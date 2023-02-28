package no.ntnu.tools;

import no.ntnu.model.Author;
import no.ntnu.model.Book;
import no.ntnu.model.Genre;
import no.ntnu.repository.AuthorRepository;
import no.ntnu.repository.BookRepository;
import no.ntnu.repository.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * A class which inserts some dummy data into the database, when Spring Boot app has started.
 */
@Component
public class DummyDataInitializer implements ApplicationListener<ApplicationReadyEvent> {
  @Autowired
  private AuthorRepository authorRepository;
  @Autowired
  private GenreRepository genreRepository;
  @Autowired
  private BookRepository bookRepository;

  private final Logger logger = LoggerFactory.getLogger("DummyInit");

  /**
   * This method is called when the application is ready (loaded).
   *
   * @param event Event which we don't use :)
   */
  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    logger.info("Importing test data...");

    Genre parody = new Genre("Parody");
    Genre networks = new Genre("Computer Networks");
    Genre psychology = new Genre("Medical Applied Psychology");
    Genre bio = new Genre("Autobiography");
    Genre web = new Genre("Web Development");

    genreRepository.save(parody);
    genreRepository.save(networks);
    genreRepository.save(psychology);
    genreRepository.save(bio);
    genreRepository.save(web);

    Book computerNetworks = new Book("Computer Networking", 2021, 800, networks);
    Book twelveRules = new Book("12 Rules for Life", 2018, 409, psychology);
    Book truthAboutChuck = new Book(
        "The Truth About Chuck Norris: 400 Facts About the World's Greatest Human",
        2007, 409, parody
    );
    Book becoming = new Book("Becoming", 2018, 448, bio);
    Book itWillHurt = new Book("This is Going to Hurt", 2018, 288, bio);
    Book webBook = new Book("Learning Web Design", 2018, 808, web);

    bookRepository.save(webBook);
    bookRepository.save(itWillHurt);
    bookRepository.save(computerNetworks);
    bookRepository.save(twelveRules);
    bookRepository.save(truthAboutChuck);
    bookRepository.save(becoming);

    Author micheleObama = new Author("Michele", "Obama", 1964);
    micheleObama.addBook(becoming);
    Author jordanPeterson = new Author("Jordan", "Peterson", 1962);
    jordanPeterson.addBook(twelveRules);
    Author jimKurose = new Author("Jim", "Kurose", 1956);
    jimKurose.addBook(computerNetworks);
    Author ianSpector = new Author("Ian", "Spector", 1988);
    ianSpector.addBook(truthAboutChuck);
    Author keithRoss = new Author("Keith", "Ross", 1957);
    keithRoss.addBook(computerNetworks);
    Author adamKay = new Author("Adam", "Kay", 1980);
    adamKay.addBook(itWillHurt);
    Author jenniferRobbins = new Author("Jennifer", "Robbins", 1980);
    jenniferRobbins.addBook(webBook);

    authorRepository.save(micheleObama);
    authorRepository.save(keithRoss);
    authorRepository.save(jordanPeterson);
    authorRepository.save(jimKurose);
    authorRepository.save(ianSpector);
    authorRepository.save(adamKay);
    authorRepository.save(jenniferRobbins);

    logger.info("DONE importing test data");
  }
}
