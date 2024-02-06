package no.ntnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * A class for handling communication with (My)SQL databases.
 * Note: the code would be the same for PostgreSQL and other databases, only the
 * connection string would inside the method `connect()` would be different.
 */
public class JdbcConnection {
  /**
   * Reference to the established connection.
   */
  private Connection connection;

  /**
   * We prevent creation of instances directly by making the constructor private!
   * The intention is to use getInstance(), in this way we enforce the singleton pattern.
   */
  private JdbcConnection() {
  }

  /**
   * Singleton instance of the class.
   */
  private static JdbcConnection instance = new JdbcConnection();

  /**
   * Get a singleton instance of the class. Use this method everywhere where you want to
   * get access to the connection.
   *
   * @return Singleton instance
   */
  public static JdbcConnection getInstance() {
    return instance;
  }

  /**
   * Connect to a database.
   *
   * @param host     Host of the database (localhost, IP address or hostname)
   * @param port     TCP port number (3306 by default)
   * @param database Database name
   * @param user     SQL user
   * @param password SQL user password
   * @throws SQLException if a database access error occurs or the url is null
   */
  public void connect(String host, int port, String database, String user, String password)
      throws SQLException {
    connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/"
        + database + "?user=" + user + "&password=" + password);
  }

  /**
   * Check if connection to the database is established.
   *
   * @return True when connection is established, false otherwise
   */
  public boolean isConnected() {
    return connection != null;
  }

  /**
   * Execute an UPDATE for the database - update address for a borrower.
   *
   * @param borrowerId ID of the borrower
   * @param address    The new address to set for the borrower
   * @return Number of borrowers which were updated
   * @throws SQLException on error
   */
  public int updateBorrowerAddress(int borrowerId, String address) throws SQLException {
    String[] parameters = new String[]{address, "" + borrowerId};
    return executeUpdateStatement("UPDATE borrowers SET address = ? WHERE id = ?", parameters);
  }

  /**
   * Get names of borrowers who borrowed a book with given title.
   *
   * @param bookTitle The book title of interest
   * @return List of all borrower names
   * @throws SQLException Exception on error (something wrong with the database
   *                      connection or the query)
   */
  public List<String> getBorrowerNames(String bookTitle) throws SQLException {
    String query = "SELECT first_name FROM borrowers b "
        + "INNER JOIN book_borrower bb ON b.id = bb.borrower_id "
        + "INNER JOIN books bo ON bo.id = bb.book_id WHERE bo.title = ?";
    return executeStringListSelectQuery(query, new String[]{bookTitle});
  }


  /**
   * Execute an SQL statement which updates the state of the database.
   *
   * @param query  SQL query, with ? in places of arguments
   * @param values List of values to replace the ? placeholders in the query
   * @return Number of updated rows
   * @throws SQLException on error
   */
  private int executeUpdateStatement(String query, String[] values) throws SQLException {
    if (!isConnected()) {
      throw new SQLException("No connection to the database");
    }
    PreparedStatement stmt = prepareStatement(query, values);
    return stmt.executeUpdate();
  }

  /**
   * Prepare a statement for a given query and arguments.
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

  /**
   * Execute a query which returns a list of strings (a single-column table).
   *
   * @param query  The SQL query
   * @param values Values to replace the "?" placeholders
   * @return List of strings, returned as rows from SQL
   * @throws SQLException Exception on error
   */
  private List<String> executeStringListSelectQuery(String query, String[] values)
      throws SQLException {
    List<String> responseStrings = new LinkedList<>();
    PreparedStatement stmt = prepareStatement(query, values);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      responseStrings.add(rs.getString(1));
    }
    return responseStrings;
  }
}
