package no.ntnu;

import java.sql.*;

/**
 * A class for handling communication with (My)SQL databases
 */
public class JdbcConnection {
    /**
     * Reference to the established connection
     */
    private Connection connection;

    /**
     * We prevent creation of instances directly by making the constructor private!
     * The intention is to use getInstance(), in this way we enforce the singleton pattern.
     */
    private JdbcConnection() {
    }

    /**
     * Singleton instance of the class
     */
    private static JdbcConnection instance;

    /**
     * Get a singleton instance of the class. Use this method everywhere where you want to get access to the connection.
     *
     * @return Singleton instance
     */
    public static JdbcConnection getInstance() {
        if (instance == null) {
            // Lazy-init the singleton instance
            instance = new JdbcConnection();
        }
        return instance;
    }

    /**
     * @param host     Host of the database (localhost, IP address or hostname)
     * @param port     TCP port number (3306 by default)
     * @param database Database name
     * @param user     SQL user
     * @param password SQL user password
     * @throws Exception Throws exception when connection not successful
     */
    public void connect(String host, int port, String database, String user, String password) throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/"
                + database + "?user=" + user + "&password=" + password);
    }

    /**
     * Check if connection to the database is established
     *
     * @return True when connection is established, false otherwise
     */
    public boolean isConnected() {
        return connection != null;
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
     * Execute an SQL statement which updates the state of the database
     *
     * @param query  SQL query, with ? in places of arguments
     * @param values List of values to replace the ? placeholders in the query
     * @throws Exception on error
     */
    private void executeUpdateStatement(String query, String[] values) throws Exception {
        if (!isConnected()) throw new Exception("No connection to the database");
        PreparedStatement stmt = prepareStatement(query, values);
        stmt.executeUpdate();
    }

    /**
     * Prepare a statement for a given query and arguments
     *
     * @param query SQL query
     * @param args  Values to replace the "?" placeholders
     * @return PreparedStatement
     * @throws SQLException Exception on error
     */
    private PreparedStatement prepareStatement(String query, String[] args) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        for (int i = 0; i < args.length; ++i) {
            stmt.setString(i + 1, args[i]);
        }
        return stmt;
    }
}
