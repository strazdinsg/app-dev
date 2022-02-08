package no.ntnu;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static no.ntnu.Week4Application.*;

/**
 * REST API controller for borrower endpoints
 */
@RestController
@RequestMapping("borrowers")
public class BorrowerController {

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
        JdbcConnection connection = connectToDb();
        if (connection != null && connection.isConnected()) {
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
     * Try to establish database connection
     *
     * @return The connection object; or null on error
     */
    private JdbcConnection connectToDb() {
        JdbcConnection connection = JdbcConnection.getInstance();
        try {
            connection.connect(DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            connection = null;
        }
        return connection;
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
        JdbcConnection connection = connectToDb();

        ResponseEntity<List<String>> response;
        if (connection != null && connection.isConnected()) {
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
