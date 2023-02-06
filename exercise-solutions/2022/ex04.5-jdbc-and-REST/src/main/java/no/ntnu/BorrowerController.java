package no.ntnu;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API controller for borrower endpoints
 */
@RestController
@RequestMapping("borrowers")
public class BorrowerController {

    private final JdbcConnection connection;

    public BorrowerController(JdbcConnection connection) {
        this.connection = connection;
    }

    /**
     * HTTP PUT mapping - update borrower in the database
     *
     * @param id      ID of the borrower
     * @param address New address, in the request body
     * @return HTTP status: 200 OK on success, BAD REQUEST if something wrong in the parameters,
     * 500 INTERNAL ERROR if something is wrong with DB connection
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody String address) {
        ResponseEntity<String> response;
        if (connection.tryConnect()) {
            try {
                connection.updateBorrowerAddress(id, address);
                response = new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                // something wrong in the user's query
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            response = new ResponseEntity<>("No connection to DB", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    /**
     * HTTP GET names of all borrowers who borrowed book with given title
     *
     * @param bookTitle Title of the book, query string (?bookTitle=...)
     * @return List of borrower names. 400 Bad request on client error; 500 Internal server error when something
     * wrong with the DB connection or SQL query.
     */
    @GetMapping()
    public ResponseEntity<List<String>> getBorrowerNames(@RequestParam String bookTitle) {
        ResponseEntity<List<String>> response;
        if (connection.tryConnect()) {
            try {
                List<String> borrowerNames = connection.getBorrowerNames(bookTitle);
                response = ResponseEntity.ok(borrowerNames);
            } catch (Exception e) {
                response = ResponseEntity.badRequest().build();
            }
        } else {
            response = ResponseEntity.internalServerError().build();
        }
        return response;
    }
}
