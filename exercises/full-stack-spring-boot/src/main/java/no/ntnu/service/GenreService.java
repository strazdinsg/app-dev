package no.ntnu.service;

import no.ntnu.repository.GenreRepository;
import no.ntnu.model.Genre;
import no.ntnu.tools.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business logic for genres
 */
@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    /**
     * Get all genres stored in the application state (database)
     * @return List of all genres
     */
    public List<Genre> getAll() {
        return Converter.iterableToList(genreRepository.findAll());
    }
}
