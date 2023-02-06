package no.ntnu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business logic related to books
 */
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    /**
     * Get all books currently stored in the application state (database)
     *
     * @return All the books
     */
    public List<Book> getAll() {
        return Converter.iterableToList(bookRepository.findAll());
    }

    /**
     * Look up a book in the application state (database)
     *
     * @param id ID of the book to look up
     * @return The book or null if none found
     */
    public Book findById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    public boolean add(Book book) {
        boolean added = false;
        if (canBeAdded(book)) {
            bookRepository.save(book);
            added = true;
        }
        return added;
    }

    /**
     * Check if the provided book can be added to the application state (database)
     *
     * @param book Book to be checked
     * @return True if the book is valid and can be added to the database
     */
    private boolean canBeAdded(Book book) {
        return book != null && book.isValid()
                && (book.getId() == null || bookRepository.findById(book.getId()).isEmpty());
    }

    /**
     * Delete a book from application state (database)
     *
     * @param bookId ID of the book to delete
     * @return true when deleted, false on error
     */
    public boolean delete(int bookId) {
        boolean deleted = false;
        if (findById(bookId) != null) {
            bookRepository.deleteById(bookId);
            deleted = true;
        }
        return deleted;
    }

    /**
     * Try to update a book in the application state (databasE)
     *
     * @param id   ID of the book to update
     * @param book The updated book values
     * @return null on success, error message on error
     */
    public String update(int id, Book book) {
        Book existingBook = findById(id);
        String errorMessage = null;
        if (existingBook == null) {
            errorMessage = "No book with id " + id + " found";
        }
        if (book == null || !book.isValid()) {
            errorMessage = "Wrong data in request body";
        } else if (book.getId() != id) {
            errorMessage = "Book ID in the URL does not match the ID in JSON data (response body)";
        }

        if (errorMessage == null) {
            bookRepository.save(book);
        }
        return errorMessage;
    }

    public List<Book> getAllByGenre(String genre) {
        return bookRepository.findByGenreNameContainingIgnoreCase(genre);
    }

    public List<Book> getAllByAuthor(String author) {
        return bookRepository.findByAuthorsFirstNameContainingIgnoreCase(author);
    }

    public List<Book> getAllByAuthorAndGenre(String author, String genre) {
        return bookRepository.findByAuthorsFirstNameContainingIgnoreCaseAndGenreNameContainingIgnoreCase(author, genre);
    }
}
