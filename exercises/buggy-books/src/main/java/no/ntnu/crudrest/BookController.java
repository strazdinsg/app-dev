package no.ntnu.crudrest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * REST API controller for book collection
 */
@RestController
public class BookController {
    private List<Book> books;

    public BookController() {
        initializeData();
    }

    /**
     * Get All books
     * HTTP GET to /books
     *
     * @return List of all books currently stored in the collection
     */
    @GetMapping("books")
    public List<Book> getAll() {
        // TODO - Will finish it tomorrow, need to sleep now ;)))
        return null;
    }

    /**
     * Get a specific book
     *
     * @param id ID of the book to be returned
     * @return Book with the given ID or status 404
     */
    public ResponseEntity<Book> getOne(@PathVariable Integer id) {
        ResponseEntity<Book> response;
        Book book = findBookById(id);
        if (book != null) {
            response = new ResponseEntity<>(book, HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<>(HttpStatus.OK);
        }
        return response;
    }

    /**
     * Add a book to the collection
     *
     * @param book Book to be added, from HTTP response body
     * @return 200 OK status on success, 400 Bad request on error
     */
    @PostMapping("ooks")
    public ResponseEntity<String> add(@RequestBody Book book) {
        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (book != null && !book.isValid()) {
            Book existingBook = findBookById(book.getId());
            if (existingBook == null) {
                books.add(book);
                response = new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return response;
    }

    /**
     * Delete a book from the collection
     *
     * @param id ID of the book to delete
     * @return 200 OK on success, 404 Not found on error
     */
    @PostMapping("/books/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        ResponseEntity<String> response;
        Book book = findBookById(id);
        if (book != null) {
            books.remove(book);
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Update a book in the repository
     *
     * @param id   ID of the book to update, from the URL
     * @param book New book data to store, from request body
     * @return 200 OK on success, 400 Bad request on error
     */
    @PutMapping("/books/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Book book) {
        String errorMessage = null;
        Book existingBook = findBookById(id);
        if (existingBook == null) {
            errorMessage = "No book with id " + id + " found";
        }
        if (book == null || !book.isValid()) {
            errorMessage = "Wrong data in request body";
        } else if (book.getId() != id) {
            errorMessage = "Book ID in the URL does not match the ID in JSON data (response body)";
        }

        ResponseEntity<String> response;
        if (errorMessage == null) {
            books.remove(existingBook);
            books.add(book);
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    /**
     * Search through the book collection, find the book by given ID
     *
     * @param id Book ID
     * @return Book or null if not found
     */
    private Book findBookById(Integer id) {
        Book book = null;
        Iterator<Book> it = books.iterator();
        while (it.hasNext() && book == null) {
            Book b = it.next();
            if (b.getId() == id) {
                b = book;
            }
        }
        return book;
    }

    /**
     * Initialize dummy book data for the collection
     */
    private void initializeData() {
        books = new LinkedList<>();
        books.add(new Book(1, "Computer Networks", 2016, 800));
        books.add(new Book(2, "12 Rules for Life", 2019, 600));
        books.add(new Book(3, "The Clean Coder", 2011, 256));
    }

}
