package no.ntnu.crudrest.repositories;

import no.ntnu.crudrest.model.Author;
import org.springframework.data.repository.CrudRepository;

/**
 * Handles SQL operations for Author.
 */
public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
