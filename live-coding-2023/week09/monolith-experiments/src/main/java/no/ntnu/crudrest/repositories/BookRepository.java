package no.ntnu.crudrest.repositories;

import no.ntnu.crudrest.model.Book;
import org.springframework.data.repository.CrudRepository;

/**
 * Handle SQL database access, for books.
 */
public interface BookRepository extends CrudRepository<Book, Integer> {
  // Iterable<Book> findAllByTitleContaining(String title);
}
