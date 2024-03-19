package no.ntnu;

import java.time.LocalDate;
import java.util.Random;
import no.ntnu.model.Author;
import no.ntnu.model.Book;
import no.ntnu.model.BookAuthor;
import no.ntnu.model.Borrower;
import no.ntnu.model.Loan;
import no.ntnu.repositories.AuthorRepository;
import no.ntnu.repositories.BookAuthorRepository;
import no.ntnu.repositories.BookRepository;
import no.ntnu.repositories.BorrowerRepository;
import no.ntnu.repositories.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * A class which inserts some dummy data into the database, when Spring Boot app has started.
 */
@Component
public class DummyDataInitializer implements ApplicationListener<ApplicationReadyEvent> {
  private static final String AUTHOR = "author";
  private static final String TRANSLATOR = "translator";
  private static final Random random = new Random();

  private final AuthorRepository authorRepository;
  private final BookAuthorRepository bookAuthorRepository;
  private final BookRepository bookRepository;
  private final BorrowerRepository borrowerRepository;
  private final LoanRepository loanRepository;

  private final Logger logger = LoggerFactory.getLogger("DummyInit");

  /**
   * Create the dummy data initializer.
   *
   * @param authorRepository     Author repository
   * @param bookAuthorRepository Book-Author relation repository
   * @param bookRepository       Book repository
   * @param borrowerRepository   Borrower repository
   * @param loanRepository       Loan repository
   */
  public DummyDataInitializer(AuthorRepository authorRepository,
                              BookAuthorRepository bookAuthorRepository,
                              BookRepository bookRepository,
                              BorrowerRepository borrowerRepository,
                              LoanRepository loanRepository) {
    this.authorRepository = authorRepository;
    this.bookAuthorRepository = bookAuthorRepository;
    this.bookRepository = bookRepository;
    this.borrowerRepository = borrowerRepository;
    this.loanRepository = loanRepository;
  }

  /**
   * This method is called when the application is ready (loaded).
   *
   * @param event Event which we don't use :)
   */
  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    logger.info("Importing test data...");

    Book computerNetworks = new Book("Computer Networking", 2021, 800);
    Book twelveRules = new Book("12 Rules for Life", 2018, 409);
    Book truthAboutChuck = new Book(
        "The Truth About Chuck Norris: 400 Facts About the World's Greatest Human",
        2007, 409);
    Book becoming = new Book("Becoming", 2018, 448);
    Book itWillHurt = new Book("This is Going to Hurt", 2018, 288);
    Book webBook = new Book("Learning Web Design", 2018, 808);
    Book myPoorPirate = new Book("My Poor Pirate", 2023, 598);

    bookRepository.save(webBook);
    bookRepository.save(itWillHurt);
    bookRepository.save(computerNetworks);
    bookRepository.save(twelveRules);
    bookRepository.save(truthAboutChuck);
    bookRepository.save(becoming);
    bookRepository.save(myPoorPirate);

    Author micheleObama = new Author("Michele", "Obama", 1964);
    Author jordanPeterson = new Author("Jordan", "Peterson", 1962);
    Author jimKurose = new Author("Jim", "Kurose", 1956);
    Author ianSpector = new Author("Ian", "Spector", 1988);
    Author keithRoss = new Author("Keith", "Ross", 1957);
    Author adamKay = new Author("Adam", "Kay", 1980);
    Author jenniferRobbins = new Author("Jennifer", "Robbins", 1980);
    Author jurgisLiepnieks = new Author("Jurgis", "Liepnieks", 1972);
    Author ievaLakute = new Author("Ieva", "Lakute", 1990);

    authorRepository.save(micheleObama);
    authorRepository.save(keithRoss);
    authorRepository.save(jordanPeterson);
    authorRepository.save(jimKurose);
    authorRepository.save(ianSpector);
    authorRepository.save(adamKay);
    authorRepository.save(jenniferRobbins);
    authorRepository.save(jurgisLiepnieks);
    authorRepository.save(ievaLakute);

    bookAuthorRepository.save(new BookAuthor(becoming, micheleObama, AUTHOR));
    bookAuthorRepository.save(new BookAuthor(computerNetworks, jimKurose, AUTHOR));
    bookAuthorRepository.save(new BookAuthor(computerNetworks, keithRoss, AUTHOR));
    bookAuthorRepository.save(new BookAuthor(twelveRules, jordanPeterson, AUTHOR));
    bookAuthorRepository.save(new BookAuthor(truthAboutChuck, ianSpector, AUTHOR));
    bookAuthorRepository.save(new BookAuthor(itWillHurt, adamKay, AUTHOR));
    bookAuthorRepository.save(new BookAuthor(webBook, jenniferRobbins, AUTHOR));
    bookAuthorRepository.save(new BookAuthor(myPoorPirate, jurgisLiepnieks, AUTHOR));
    bookAuthorRepository.save(new BookAuthor(myPoorPirate, ievaLakute, TRANSLATOR));

    Borrower john = new Borrower("john@doe.com", "John", "Doe", "Baker Street 12");
    Borrower sally = new Borrower("sally@softromic.com", "Sally", "Sanders", "New Street 34");
    Borrower chuck = new Borrower("apple@chuck.com", "Chuck", "Dorris",
        "P.O. Box 872, Navasota, TX 77868-0872 USA");
    borrowerRepository.save(john);
    borrowerRepository.save(sally);
    borrowerRepository.save(chuck);

    saveLoan(john, computerNetworks);
    saveLoan(john, webBook);
    saveLoan(sally, becoming);
    saveLoan(sally, itWillHurt);
    saveLoan(chuck, truthAboutChuck);

    logger.info("DONE importing test data");
  }

  private void saveLoan(Borrower borrower, Book book) {
    LocalDate fromDate = createRandomDate();
    LocalDate toDate = addRandomDaysTo(fromDate);
    loanRepository.save(new Loan(book, borrower, fromDate, toDate));
  }

  /**
   * Create a random date around, near the current date.
   *
   * @return A random date
   */
  private LocalDate createRandomDate() {
    return LocalDate.now().minusDays(random.nextLong(30));
  }

  /**
   * Create a date which is some random number of days in the future relative to fromDate.
   *
   * @param fromDate The fromDate which functions as the minimum date for the result
   * @return A random date which is in the future relative to fromDate.
   */
  private LocalDate addRandomDaysTo(LocalDate fromDate) {
    return fromDate.plusDays(random.nextLong(30));
  }

}