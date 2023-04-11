package no.ntnu.repositories;

import no.ntnu.model.Author;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing Author data in the database.
 * Spring will auto-generate necessary methods.
 */
public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
