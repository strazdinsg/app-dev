package no.ntnu.crudrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Initialize some dummy test data in the SQL database. Do it only once - when the tables are empty.
 */
@Component
public class DummyDataInitializer implements ApplicationListener<ApplicationReadyEvent> {
  private static final Logger logger = LoggerFactory.getLogger(DummyDataInitializer.class);

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private AuthorRepository authorRepository;

  /**
   * This method is called when all the components in the Spring Boot app are successfully started
   *
   * @param event The on-started event, not used here
   */
  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    logger.info("Importing dummy data...");

    if (authorRepository.count() == 0) {
      logger.info("No books in the database, adding some...");
      Author jim = new Author(0, "Jim", "Kurose", 1970);
      Author keith = new Author(0, "Keith", "Ross", 1971);
      Author jordan = new Author(0, "Jordan", "Peterson", 1972);
      Author jennifer = new Author(0, "Jennifer", "Robbins", 1973);
      authorRepository.save(jim);
      authorRepository.save(keith);
      authorRepository.save(jordan);
      authorRepository.save(jennifer);

      Book compNet = new Book(0, "Computer Networks", 2012, 700);
      Book web = new Book(0, "Learning Web design", 2013, 800);
      Book rules = new Book(0, "12 Rules for Life", 2014, 900);

      compNet.getAuthors().add(jim);
      compNet.getAuthors().add(keith);
      web.getAuthors().add(jennifer);
      rules.getAuthors().add(jordan);

      bookRepository.save(compNet);
      bookRepository.save(web);
      bookRepository.save(rules);
    }
  }
}
