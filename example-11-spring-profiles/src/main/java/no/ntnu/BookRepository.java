package no.ntnu;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for accessing Book data in the database.
 * Spring will auto-generate necessary methods.
 */
public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findByGenreNameContainingIgnoreCase(String genre);

    List<Book> findByAuthorsFirstNameContainingIgnoreCase(String author);

    List<Book> findByAuthorsFirstNameContainingIgnoreCaseAndGenreNameContainingIgnoreCase(String author, String genre);
}
