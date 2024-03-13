package no.ntnu.service;

import no.ntnu.model.Book;
import no.ntnu.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Business logic related to books.
 */
@Service
public class BookService {
  @Autowired
  private BookRepository bookRepository;

  /**
   * Get all books currently stored in the application state (database).
   *
   * @return All the books
   */
  public Iterable<Book> getAll() {
    return bookRepository.findAll();
  }

  /**
   * Get the first n books from the database.
   *
   * @param n The number of books to select
   * @return An iterable over the books, empty when no books found.
   */
  public Iterable<Book> getFirst(int n) {
    return bookRepository.findAll(PageRequest.of(0, n));
  }

  /**
   * Get the number of books in the database.
   *
   * @return The total number of books stored in the database.
   */
  public long getCount() {
    return bookRepository.count();
  }
}
