package no.ntnu.controllers;

import static no.ntnu.controllers.ControllerCommons.createResponse;

import no.ntnu.BookService;
import no.ntnu.model.Book;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API controller for book collection.
 */
@RestController
@RequestMapping("/books")
public class BookController {
  @Autowired
  private BookService bookService;

  /**
   * Get All books.
   * HTTP GET to /books
   *
   * @param genre  Genre. When specified, get all books with a genre including this substring,
   *               case-insensitive.
   * @param author Author. When specified, get all books with an author including this substring,
   *               case-insensitive.
   * @return List of all books currently stored in the collection
   */
  @GetMapping
  public Iterable<Book> getAll(@RequestParam(required = false) String genre,
                           @RequestParam(required = false) String author) {
    if (genre != null && !"".equals(genre)) {
      if (author != null && !"".equals(author)) {
        return bookService.getAllByAuthorAndGenre(author, genre);
      } else {
        return bookService.getAllByGenre(genre);
      }
    } else if (author != null && !"".equals(author)) {
      return bookService.getAllByAuthor(author);
    } else {
      return bookService.getAll();
    }
  }

  /**
   * Get a specific book.
   *
   * @param id ID of the book to be returned
   * @return Book with the given ID or status 404
   */
  @GetMapping("/{id}")
  public ResponseEntity<Book> getOne(@PathVariable Integer id) {
    ResponseEntity<Book> response;
    Book book = bookService.findById(id);
    if (book != null) {
      response = new ResponseEntity<>(book, HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * Add a book to the collection.
   *
   * @param book Book to be added, from HTTP response body
   * @return 200 OK status on success, 400 Bad request on error
   */
  @PostMapping
  public ResponseEntity<String> add(@RequestBody Book book) {
    ResponseEntity<String> response;
    if (bookService.add(book)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
   * @return 200 OK on success, 400 Bad request on error
   */
  @PutMapping("/{id}")
  public ResponseEntity<String> update(@PathVariable int id, @RequestBody Book book) {
    return createResponse(bookService.update(id, book));
  }
}
