package no.ntnu.crudrest;

import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.*;

/**
 * REST API controller for book collection
 */
@RestController
@RequestMapping("/books")
public class BookController {
//  private Map<Integer, Book> books;
//  private int latestId;

//  public BookController() {
//    initializeData();
//  }

  /**
   * Initialize dummy book data for the collection
   */
//  private void initializeData() {
//    latestId = 1;
//    books = new HashMap<>();
//    addBookToCollection(new Book(-1, "Computer Networks", 2016, 800));
//    addBookToCollection(new Book(-1, "12 Rules for Life", 2019, 600));
//  }

//  private int createNewId() {
//    return latestId++;
//  }

  /**
   * Get All books
   * HTTP GET to /books
   *
   * @return List of all books currently stored in the collection; or 500 code on error.
   */
  @GetMapping
  @Operation(
      summary = "Get all books",
      description = "List of all books currently stored in the collection"
  )
  public ResponseEntity<Object> getAll() {
    // Establish connection to SQL database
    Connection connection;
    try {
      connection = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/library?user=libuser&password=libuser23!");
      System.out.println("Connection established");
    } catch (SQLException e) {
      return handleDatabaseError(e);
    }

    // Execute query
    ResultSet resultSet;
    try {
      String query = "SELECT * FROM `books`";
      PreparedStatement statement = connection.prepareStatement(query);
      resultSet = statement.executeQuery();
    } catch (SQLException e) {
      return handleDatabaseError(e);
    }

    // Parse the response data
    List<Book> books = new LinkedList<>();
    try {
      while (resultSet.next()) {
        Book book = new Book(
            resultSet.getInt("id"),
            resultSet.getString("title"),
            resultSet.getInt("year"),
            resultSet.getInt("number_of_pages")
        );
        books.add(book);
      }
    } catch (SQLException e) {
      return handleDatabaseError(e);
    }

    return new ResponseEntity<>(books, HttpStatus.OK);
  }

  private ResponseEntity<Object> handleDatabaseError(SQLException e) {
    System.out.println("SQL database error: " + e.getMessage());
    return new ResponseEntity<>("Database error, contact the admin!", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Get a specific book
   *
   * @param id ID` of the book to be returned
   * @return Book with the given ID or status 404
   */
  @GetMapping("/{id}")
  public ResponseEntity<Book> getOne(@PathVariable Integer id) {
    ResponseEntity<Book> response;
    Book book = findBookById(id);
    if (book != null) {
      response = new ResponseEntity<>(book, HttpStatus.OK);
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
   * 400 Bad request if some data is missing or incorrect
   */
  @PostMapping()
  @Operation(deprecated = true)
  ResponseEntity<String> add(@RequestBody Book book) {
    ResponseEntity<String> response;

    try {
      addBookToCollection(book);
      response = new ResponseEntity<>("" + book.getId(), HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Delete a book from the collection
   *
   * @param id ID of the book to delete
   * @return 200 OK on success, 404 Not found on error
   */
  @DeleteMapping("/{id}")
  @Operation(hidden = true)
  public ResponseEntity<String> delete(@PathVariable int id) {
    ResponseEntity<String> response;
    if (removeBookFromCollection(id)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * Remove a book from the collection.
   *
   * @param id ID of the book to remove
   * @return True when book with that ID existed and was removed, false otherwise.
   */
  private boolean removeBookFromCollection(int id) {
    // TODO - implement
//    Book removedBook = books.remove(id);
//    return removedBook != null;
    return false;
  }

  /**
   * Update a book in the repository
   *
   * @param id   ID of the book to update, from the URL
   * @param book New book data to store, from request body
   * @return 200 OK on success, 400 Bad request on error
   */
  @PutMapping("/{id}")
  public ResponseEntity<String> update(@PathVariable int id, @RequestBody Book book) {
    ResponseEntity<String> response;
    try {
      updateBook(id, book);
      response = new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Search through the book collection, find the book by given ID
   *
   * @param id Book ID
   * @return Book or null if not found
   */
  private Book findBookById(Integer id) {
    // TODO - implement
//    return books.get(id);
    return null;
  }

  private void addBookToCollection(Book book) throws IllegalArgumentException {
    // TODO - implement
    throw new NotImplementedException("Not implemented yet");

//    if (!book.isValid()) {
//      throw new IllegalArgumentException("Book is invalid");
//    }
//    book.setId(createNewId());
//    books.put(book.getId(), book);
  }

  /**
   * Try to update a book with given ID. The book.id must match the id.
   *
   * @param id   ID of the book
   * @param book The updated book data
   * @throws IllegalArgumentException If something goes wrong.
   *                                  Error message can be used in HTTP response.
   */
  private void updateBook(int id, Book book) throws IllegalArgumentException {

    Book existingBook = findBookById(id);
    if (existingBook == null) {
      throw new IllegalArgumentException("No book with id " + id + " found");
    }
    if (book == null || !book.isValid()) {
      throw new IllegalArgumentException("Wrong data in request body");
    }
    if (book.getId() != id) {
      throw new IllegalArgumentException(
          "Book ID in the URL does not match the ID in JSON data (response body)");
    }

    // TODO - implement
    throw new NotImplementedException("Not implemented yet");
  }
}
