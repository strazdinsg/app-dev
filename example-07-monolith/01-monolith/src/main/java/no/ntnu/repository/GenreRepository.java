package no.ntnu.repository;

import no.ntnu.model.Genre;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing Gende data in the database.
 * Spring will auto-generate necessary methods.
 */
public interface GenreRepository extends CrudRepository<Genre, Integer> {
}
