package no.ntnu;

import no.ntnu.model.Author;
import no.ntnu.model.Book;
import no.ntnu.model.Genre;
import no.ntnu.repositories.AuthorRepository;
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
    Genre biography = new Genre("Autobiography");

    genreRepository.save(parody);
    genreRepository.save(networks);
    genreRepository.save(psychology);
    genreRepository.save(biography);

    Book computerNetworking = new Book("Computer Networking", 2021, 800, networks);
    Book twelveRules = new Book("12 Rules for Life", 2018, 409, psychology);
    Book chuckBook = new Book(
        "The Truth About Chuck Norris: 400 Facts About the World's Greatest Human",
        2007, 409, parody);
    Book becoming = new Book("Becoming", 2018, 448, biography);

    bookRepository.save(computerNetworking);
    bookRepository.save(twelveRules);
    bookRepository.save(chuckBook);
    bookRepository.save(becoming);

    Author kurose = new Author("Jim", "Kurose", 1956);
    kurose.addBook(computerNetworking);
    Author ross = new Author("Keith", "Ross", 1957);
    ross.addBook(computerNetworking);
    Author peterson = new Author("Jordan", "Peterson", 1962);
    peterson.addBook(twelveRules);
    Author spector = new Author("Ian", "Spector", 1988);
    spector.addBook(chuckBook);
    Author obama = new Author("Michele", "Obama", 1964);
    obama.addBook(becoming);

    authorRepository.save(obama);
    authorRepository.save(ross);
    authorRepository.save(peterson);
    authorRepository.save(kurose);
    authorRepository.save(spector);

    logger.info("DONE importing test data");
  }
}
