package no.ntnu.crudrest;

import org.springframework.data.repository.CrudRepository;

/**
 * Handle SQL database access, for books.
 */
public interface BookRepository extends CrudRepository<Book, Integer> {
  // Iterable<Book> findAllByTitleContaining(String title);
}
