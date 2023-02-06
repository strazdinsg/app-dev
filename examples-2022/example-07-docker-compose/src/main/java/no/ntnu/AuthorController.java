package no.ntnu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API controller for author collection
 */
@RestController
@RequestMapping("authors")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    /**
     * Get All authors
     * HTTP GET to /authors
     *
     * @return List of all authors currently stored in the collection
     */
    @GetMapping
    public List<Author> getAll() {
        return authorService.getAll();
    }

    /**
     * Get a specific author
     *
     * @param id ID of the author to be returned
     * @return Author with the given ID or status 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<Author> getOne(@PathVariable Integer id) {
        ResponseEntity<Author> response;
        Author author = authorService.findById(id);
        if (author != null) {
            response = new ResponseEntity<>(author, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Add an author to the collection
     *
     * @param author Author to be added, from HTTP response body
     * @return 200 OK status on success, 400 Bad request on error
     */
    @PostMapping
    public ResponseEntity<String> add(@RequestBody Author author) {
        ResponseEntity<String> response;
        if (authorService.add(author)) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    /**
     * Delete an author from the collection
     *
     * @param id ID of the author to delete
     * @return 200 OK on success, 404 Not found on error
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        ResponseEntity<String> response;
        if (authorService.remove(id)) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Update an author in the repository
     *
     * @param id     ID of the author to update, from the URL
     * @param author New author data to store, from request body
     * @return 200 OK on success, 400 Bad request on error
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Author author) {
        String errorMessage = authorService.update(id, author);
        ResponseEntity<String> response;
        if (errorMessage == null) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
