package no.ntnu.crudrest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * REST API controller for author collection
 */
@RestController
@RequestMapping("authors")
public class AuthorController {
  private List<Author> authors;

  public AuthorController() {
    initializeData();
  }

  /**
   * Initialize dummy author data for the collection
   */
  private void initializeData() {
    authors = new LinkedList<>();
    authors.add(new Author(1, "James", "Kurose", 1960));
    authors.add(new Author(2, "Keith", "Ross", 1965));
    authors.add(new Author(3, "Jordan", "Peterson", 1960));
  }

  /**
   * Get All authors
   * HTTP GET to /authors
   *
   * @return List of all authors currently stored in the collection
   */
  @GetMapping
  public List<Author> getAll() {
    return authors;
  }

  /**
   * Get a specific author
   *
   * @param id ID of the author to be returned
   * @return Author with the given ID or status 404
   */
  @GetMapping("/{id}")
  public ResponseEntity<Author> getOne(@PathVariable Integer id) {
    ResponseEntity<Author> response;
    Author author = findAuthorById(id);
    if (author != null) {
      response = new ResponseEntity<>(author, HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * Add an author to the collection
   *
   * @param author Author to be added, from HTTP response body
   * @return 200 OK status on success, 400 Bad request on error
   */
  @PostMapping
  public ResponseEntity<String> add(@RequestBody Author author) {
    ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    if (author != null && author.isValid()) {
      Author existingAuthor = findAuthorById(author.getId());
      if (existingAuthor == null) {
        authors.add(author);
        response = new ResponseEntity<>(HttpStatus.OK);
      }
    }
    return response;
  }

  /**
   * Delete an author from the collection
   *
   * @param id ID of the author to delete
   * @return 200 OK on success, 404 Not found on error
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
    ResponseEntity<String> response;
    Author author = findAuthorById(id);
    if (author != null) {
      authors.remove(author);
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * Update an author in the repository
   *
   * @param id     ID of the author to update, from the URL
   * @param author New author data to store, from request body
   * @return 200 OK on success, 400 Bad request on error
   */
  @PutMapping("/{id}")
  public ResponseEntity<String> update(@PathVariable int id, @RequestBody Author author) {
    String errorMessage = null;
    Author existingAuthor = findAuthorById(id);
    if (existingAuthor == null) {
      errorMessage = "No author with id " + id + " found";
    }
    if (author == null || !author.isValid()) {
      errorMessage = "Wrong data in request body";
    } else if (author.getId() != id) {
      errorMessage = "Author ID in the URL does not match the ID in JSON data (response body)";
    }

    ResponseEntity<String> response;
    if (errorMessage == null) {
      authors.remove(existingAuthor);
      authors.add(author);
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Search through the author collection, find the author by given ID
   *
   * @param id Author ID
   * @return Author or null if not found
   */
  private Author findAuthorById(Integer id) {
    Author author = null;
    Iterator<Author> it = authors.iterator();
    while (it.hasNext() && author == null) {
      Author b = it.next();
      if (b.getId() == id) {
        author = b;
      }
    }
    return author;
  }
}
