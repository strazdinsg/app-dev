package no.ntnu.services;

import no.ntnu.GenreRepository;
import no.ntnu.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic for genres.
 */
@Service
public class GenreService {
  @Autowired
  private GenreRepository genreRepository;

  /**
   * Get all genres stored in the application state (database).
   *
   * @return List of all genres
   */
  public Iterable<Genre> getAll() {
    return genreRepository.findAll();
  }
}
