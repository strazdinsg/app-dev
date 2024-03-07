package no.ntnu.crudrest;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API controller for book collection.
 */
@RestController
@RequestMapping("/books")
public class BookController {
  private static final Logger logger = LoggerFactory.getLogger(BookController.class);

  @Autowired
  private BookService bookService;


  /**
   * Get All books.
   * HTTP GET to /books
   *
   * @return List of all books currently stored in the collection
   */
  @GetMapping
  public Iterable<Book> getAll() {
    logger.warn("Getting all books");
    return bookService.getAll();
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
    Optional<Book> book = bookService.findById(id);
    if (book.isPresent()) {
      response = new ResponseEntity<>(book.get(), HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * HTTP POST endpoint for adding a new book.
   *
   * @param book Data of the book to add. ID will be ignored.
   * @return 201 Created on success and the new ID in the response body,
   *     400 Bad request if some data is missing or incorrect
   */
  @PostMapping()
  ResponseEntity<String> add(@RequestBody Book book) {
    ResponseEntity<String> response;
    try {
      int id = bookService.add(book);
      response = new ResponseEntity<>("" + id, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Delete a book from the collection.
   *
   * @param id ID of the book to delete
   * @return 200 OK on success, 404 Not found on error
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
    ResponseEntity<String> response;
    if (bookService.delete(id)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * Update a book in the repository.
   *
   * @param id   ID of the book to update, from the URL
   * @param book New book data to store, from request body
   * @return 200 OK on success, 400 Bad request on error with error message in the response body
   */
  @PutMapping("/{id}")
  public ResponseEntity<String> update(@PathVariable int id, @RequestBody Book book) {
    ResponseEntity<String> response;
    try {
      bookService.update(id, book);
      response = new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return response;
  }
}
