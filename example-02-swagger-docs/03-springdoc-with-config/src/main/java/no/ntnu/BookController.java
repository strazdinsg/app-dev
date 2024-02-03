package no.ntnu;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST API controller for all endpoints related to books.
 */
@RestController
public class BookController {

  // We will use some static data
  List<Book> books = new ArrayList<>();

  BookController() {
    initializeData();
  }

  private void initializeData() {
    books.add(new Book(1, "Learning Web Design", 2008, 500));
    books.add(new Book(2, "12 Rules for Life", 2016, 300));
    books.add(new Book(3, "Me Before You", 2000, 400));
  }

  /**
   * HTTP GET endpoint for getting all the books.
   *
   * @return List of all the books in the collection.
   */
  @GetMapping("/books")
  @Deprecated
  @Operation(
      summary = "Get all books",
      description = "Note: the deprecation is only as an example."
  )
  public Iterable<Book> getBooks() {
    return books;
  }

  /**
   * HTTP GET endpoint for getting one particular book.
   *
   * @param id ID of the book
   * @return The book, or 404 code if not found.
   */
  @GetMapping("/books/{id}")
  @Operation(
      summary = "Get one book",
      description = "Get a single book, with the given ID"
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The book returned in the response body"
      ),
      @ApiResponse(
          responseCode = "404",
          description = "The book with the provided ID is not found, empty body",
          content = @Content
      )
  })
  public ResponseEntity<Book> getBook(
      @Parameter(description = "The ID of the book to fetch")
      @PathVariable
      int id
  ) {
    ResponseEntity<Book> response;
    Book book = findBookById(id);
    if (book != null) {
      response = ResponseEntity.ok(book);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return response;
  }

  /**
   * Find a book by ID.
   *
   * @param id ID of the book to look for
   * @return The book found or null if none found by the given ID
   */
  private Book findBookById(int id) {
    Book foundBook = null;
    Iterator<Book> it = books.iterator();

    while (foundBook == null && it.hasNext()) {
      Book b = it.next();
      if (b.id() == id) {
        foundBook = b;
      }
    }

    return foundBook;
  }

  /**
   * HTTP POST endpoint for adding a new book.
   *
   * @param book Data of the book to add. ID will be ignored.
   * @return 201 Created on success, 400 Bad request if some data is missing or incorrect
   */
  @PostMapping("/books")
  ResponseEntity<String> addBook(@RequestBody Book book) {
    ResponseEntity<String> response;

    try {
      addBookToCollection(book);
      response = new ResponseEntity<>(HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  private void addBookToCollection(Book book) throws IllegalArgumentException {
    checkIfBookIsValid(book);
    int newId = books.size() + 1;
    Book copy = new Book(newId, book.title(), book.year(), book.numberOfPages());
    books.add(copy);
  }

  private void checkIfBookIsValid(Book book) throws IllegalArgumentException {
    if (book.title() == null) {
      throw new IllegalArgumentException("Book title can't be null");
    }
  }
}
