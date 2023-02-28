package no.ntnu.controllers;

import no.ntnu.model.Book;
import no.ntnu.model.Statistics;
import no.ntnu.service.AuthorService;
import no.ntnu.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles REST API requests for data.
 */
@RequestMapping("/api")
@RestController
public class RestApiController {
  private static Logger logger = LoggerFactory.getLogger(RestApiController.class);

  @Autowired
  BookService bookService;
  @Autowired
  AuthorService authorService;

  /**
   * Get all books stored in the database.
   *
   * @return JSON data of the books
   */
  @GetMapping("/books")
  public Iterable<Book> getAllBooks() {
    emulateDelay(5000);
    return bookService.getAll();
  }

  /**
   * Get favorite books stored in the database.
   *
   * @return JSON data of the books
   */
  @GetMapping("/books/favorite")
  public Iterable<Book> getFavoriteBooks() {
    emulateDelay(10000);
    return bookService.getFirst(2).toList();
  }

  /**
   * Get statistics for the stored data.
   *
   * @return The statistics
   */
  @GetMapping("/statistics")
  public Statistics getStatistics() {
    emulateDelay(15000);
    long bookCount = bookService.getCount();
    long authorCount = authorService.getCount();
    return new Statistics(bookCount, authorCount);
  }

  /**
   * Emulate a delay of a long operation.
   *
   * @param duration The duration of the delay, in milliseconds.
   */
  private void emulateDelay(int duration) {
    try {
      Thread.sleep(duration);
    } catch (InterruptedException e) {
      logger.warn("Disturbed during a sleep...");
      Thread.currentThread().interrupt();
    }
  }
}
