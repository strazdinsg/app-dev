package no.ntnu;

import java.util.List;
import no.ntnu.model.Book;
import org.springframework.data.repository.CrudRepository;


/**
 * Repository interface for accessing Book data in the database.
 * Spring will auto-generate necessary methods.
 */
public interface BookRepository extends CrudRepository<Book, Integer> {
  List<Book> findByGenreNameContainingIgnoreCase(String genre);

  List<Book> findByAuthorsFirstNameContainingIgnoreCase(String author);

  List<Book> findByAuthorsFirstNameContainingIgnoreCaseAndGenreNameContainingIgnoreCase(
      String author, String genre);
}
