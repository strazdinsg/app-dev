package no.ntnu.controllers;

import java.util.Optional;
import no.ntnu.model.Book;
import no.ntnu.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API controller for book collection.
 */
@RestController
@RequestMapping("/books")
public class BookController {
  private static final Logger logger = LoggerFactory.getLogger(BookController.class);

  private final BookRepository bookRepository;

  public BookController(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  /**
   * Get All books.
   * HTTP GET to /books
   *
   * @return List of all books currently stored in the collection
   */
  @GetMapping
  public Iterable<Book> getAll() {
    logger.warn("Getting all books");
    return bookRepository.findAll();
  }

  /**
   * Get a specific book.
   *
   * @param id ID` of the book to be returned
   * @return Book with the given ID or status 404
   */
  @GetMapping("/{id}")
  public ResponseEntity<Book> getOne(@PathVariable Integer id) {
    ResponseEntity<Book> response;
    Optional<Book> book = bookRepository.findById(id);
    if (book.isPresent()) {
      response = new ResponseEntity<>(book.get(), HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return response;
  }
}
