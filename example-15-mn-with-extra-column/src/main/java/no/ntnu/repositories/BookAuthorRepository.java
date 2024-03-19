package no.ntnu.repositories;

import no.ntnu.model.BookAuthor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for SQL access to our database table.
 */
@Repository
public interface BookAuthorRepository extends CrudRepository<BookAuthor, Integer> {
}
