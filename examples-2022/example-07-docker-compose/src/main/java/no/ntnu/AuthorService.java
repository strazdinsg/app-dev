package no.ntnu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Business logic related to authors
 */
@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Get all authors from the application state
     *
     * @return A list of authors, empty list if there are none
     */
    public List<Author> getAll() {
        return Converter.iterableToList(authorRepository.findAll());
    }


    /**
     * Look up author in the application state
     *
     * @param id ID of the author to look up
     * @return The author or null if none found by that ID
     */
    public Author findById(int id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.orElse(null);
    }

    /**
     * Add an author to the application state (persist in the database)
     *
     * @param author Author to persist
     * @return true when author added, false on error
     */
    public boolean add(Author author) {
        boolean added = false;
        if (author != null && author.isValid()) {
            Author existingAuthor = findById(author.getId());
            if (existingAuthor == null) {
                authorRepository.save(author);
                added = true;
            }
        }
        return added;
    }

    /**
     * Remove an author from application state (database)
     *
     * @param authorId ID of the author to delete
     * @return ture when author deleted, false when author was not found in the database
     */
    public boolean remove(int authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            authorRepository.delete(author.get());
        }
        return author.isPresent();
    }

    /**
     * Update an author in the application state (persist in the database)
     *
     * @param author Author to update
     * @return null on success, error message on error
     */
    public String update(Integer id, Author author) {
        // Error checking first
        String errorMessage = null;
        Author existingAuthor = findById(id);
        if (existingAuthor == null) {
            errorMessage = "No author with id " + id + " found";
        } else if (author == null || !author.isValid()) {
            errorMessage = "Wrong data in request body";
        } else if (author.getId() != id) {
            errorMessage = "Author ID in the URL does not match the ID in JSON data (response body)";
        }

        if (errorMessage == null) {
            authorRepository.save(author);
        }
        return errorMessage;
    }
}
