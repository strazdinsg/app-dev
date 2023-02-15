package no.ntnu.crudrest;

import org.springframework.data.repository.CrudRepository;

/**
 * Handles SQL operations for Author.
 */
public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
