package no.ntnu;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing Author data in the database.
 * Spring will auto-generate necessary methods.
 */
public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
