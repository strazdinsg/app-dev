package no.ntnu;

import no.ntnu.model.Genre;
import org.springframework.data.repository.CrudRepository;

/**
 * Handles Genre-related database operations.
 */
public interface GenreRepository extends CrudRepository<Genre, Integer> {
}
