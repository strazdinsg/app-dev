package no.ntnu.crudrest;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic for books.
 */
@Service
public class BookService {
  @Autowired
  private BookRepository bookRepository;
  /**
   * Add a new book to the collection. Note: ID will be auto-generated, the original ID will
   * not be used!
   *
   * @param book The book to add
   * @return the ID of the added book
   * @throws IllegalArgumentException When the provided book is not valid
   */
  public int add(Book book) throws IllegalArgumentException {
    if (!book.isValid()) {
      throw new IllegalArgumentException("Book is invalid");
    }
    bookRepository.save(book);
    return book.getId();
  }

  public Iterable<Book> getAll() {
    return bookRepository.findAll();
  }

  public Optional<Book> findById(int id) {
    return bookRepository.findById(id);
  }

  /**
   * Try to delete a book with the given ID.
   *
   * @param id ID of the book to delete
   * @return True if the book was found and got deleted. False otherwise.
   */
  public boolean delete(int id) {
    Optional<Book> book = bookRepository.findById(id);
    if (book.isPresent()) {
      bookRepository.deleteById(id);
    }
    return book.isPresent();
  }

  /**
   * Try to update a book with given ID. The book.id must match the id.
   *
   * @param id   ID of the book
   * @param book The updated book data
   * @throws IllegalArgumentException If something goes wrong.
   *                                  Error message can be used in HTTP response.
   */
  public void update(int id, Book book) throws IllegalArgumentException {
    Optional<Book> existingBook = bookRepository.findById(id);
    if (existingBook.isEmpty()) {
      throw new IllegalArgumentException("No book with id " + id + " found");
    }
    if (book == null || !book.isValid()) {
      throw new IllegalArgumentException("Wrong data in request body");
    }
    if (book.getId() != id) {
      throw new IllegalArgumentException(
          "Book ID in the URL does not match the ID in JSON data (response body)");
    }

    bookRepository.save(book);
  }

}
