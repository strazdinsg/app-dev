package no.ntnu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * A class for handling communication with (My)SQL databases
 */
@Component
public class JdbcConnection {
    @Value("${db.host}")
    private String host;
    @Value("${db.port}")
    private int port;
    @Value("${db.name}")
    private String dbName;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Reference to the established connection
     */
    private Connection connection;

    /**
     * Establish connection to the database
     *
     * @return true on success, false on error
     */
    private boolean connect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/"
                        + dbName + "?user=" + user + "&password=" + password);
            } catch (SQLException e) {
                connection = null;
            }
        }
        return connection != null;
    }

    /**
     * Try to connect to the database. If connection is already established, don't reconnect.
     *
     * @return True when connection is established, false otherwise
     */
    public boolean tryConnect() {
        return connect();
    }

    /**
     * Execute an UPDATE for the database - update address for a borrower
     *
     * @param borrowerId ID of the borrower
     * @param address    The new address to set for the borrower
     * @throws Exception on error
     */
    public void updateBorrowerAddress(int borrowerId, String address) throws Exception {
        String[] parameters = new String[]{address, "" + borrowerId};
        executeUpdateStatement("UPDATE borrowers SET address = ? WHERE id = ?", parameters);
    }

    /**
     * Get names of borrowers who borrowed a book with given title
     *
     * @param bookTitle The book title of interest
     * @return List of all borrower names
     * @throws Exception on error (something wrong with the database connection or the query)
     */
    public List<String> getBorrowerNames(String bookTitle) throws Exception {
        String query = "SELECT first_name FROM borrowers b INNER JOIN book_borrower bb ON b.id = bb.borrower_id " +
                "INNER JOIN books bo ON bo.id = bb.book_id WHERE bo.title = ?";
        return executeStringListSelectQuery(query, new String[]{bookTitle});
    }

    /**
     * Execute an SQL statement which updates the state of the database
     *
     * @param query  SQL query, with ? in places of arguments
     * @param values List of values to replace the ? placeholders in the query
     * @throws Exception on error
     */
    private void executeUpdateStatement(String query, String[] values) throws Exception {
        PreparedStatement stmt = prepareStatement(query, values);
        stmt.executeUpdate();
    }

    /**
     * Prepare a statement for a given query and arguments
     *
     * @param query  SQL query
     * @param values Values to replace the "?" placeholders
     * @return PreparedStatement
     * @throws Exception on error
     */
    private PreparedStatement prepareStatement(String query, String[] values) throws Exception {
        connect();
        PreparedStatement stmt = connection.prepareStatement(query);
        for (int i = 0; i < values.length; ++i) {
            stmt.setString(i + 1, values[i]);
        }
        return stmt;
    }

    /**
     * Execute a query which returns a list of strings (a single-column table)
     *
     * @param query  The SQL query
     * @param values Values to replace the "?" placeholders
     * @return List of strings, returned as rows from SQL
     * @throws Exception on error
     */
    private List<String> executeStringListSelectQuery(String query, String[] values) throws Exception {
        PreparedStatement stmt = prepareStatement(query, values);
        ResultSet rs = stmt.executeQuery();
        List<String> responseStrings = new LinkedList<>();
        while (rs.next()) {
            responseStrings.add(rs.getString(1));
        }
        return responseStrings;
    }

}
