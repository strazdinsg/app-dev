package no.ntnu.repositories;

import no.ntnu.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for SQL access to our database table.
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
