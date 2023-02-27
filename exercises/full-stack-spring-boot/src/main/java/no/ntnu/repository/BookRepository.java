package no.ntnu.repository;

import no.ntnu.model.Book;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing Book data in the database.
 * Spring will auto-generate necessary methods.
 */
public interface BookRepository extends CrudRepository<Book, Integer> {
  Iterable<Book> findByGenreNameContainingIgnoreCase(String genre);

  Iterable<Book> findByAuthorsFirstNameContainingIgnoreCase(String author);

  Iterable<Book> findByAuthorsFirstNameContainingIgnoreCaseAndGenreNameContainingIgnoreCase(String author, String genre);
}
