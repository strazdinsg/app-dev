package no.ntnu;

import java.sql.*;
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

  // Mapper for Book objects. Note: normally you would move it into more specific class
  // (such as BookRepository)
  private BookRowMapper bookRowMapper = new BookRowMapper();

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
   * Establish connection to the SQL database.
   *
   * @param host     Host of the database (localhost, IP address or hostname)
   * @param port     TCP port number (3306 by default)
   * @param database Database name
   * @param user     SQL user
   * @param password SQL user password
   * @throws Exception Throws exception when connection not successful
   */
  public void connect(String host, int port, String database, String user, String password)
      throws Exception {
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
   * @throws Exception on error
   */
  public int updateBorrowerAddress(int borrowerId, String address) throws Exception {
    String[] parameters = new String[]{address, "" + borrowerId};
    return executeUpdateStatement("UPDATE borrowers SET address = ? WHERE id = ?", parameters);
  }

  /**
   * Get names of borrowers who borrowed a book with given title.
   *
   * @param bookTitle The book title of interest
   * @return List of all borrower names
   * @throws SQLException Exception on error (something wrong with the database connection
   *                      or the query)
   */
  public List<String> getBorrowerNames(String bookTitle) throws SQLException {
    String query = "SELECT first_name FROM borrowers b INNER JOIN book_borrower bb "
        + "ON b.id = bb.borrower_id "
        + "INNER JOIN books bo ON bo.id = bb.book_id WHERE bo.title = ?";
    return executeStringListSelectQuery(query, new String[]{bookTitle});
  }

  public List<Book> getAllBooks() {
    return executeSelectQuery("SELECT * FROM `books`", new String[]{}, bookRowMapper);
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
   */
  private List<String> executeStringListSelectQuery(String query, String[] values)
      throws SQLException {
    PreparedStatement stmt = prepareStatement(query, values);
    ResultSet rs = stmt.executeQuery();
    List<String> responseStrings = new LinkedList<>();
    while (rs.next()) {
      responseStrings.add(rs.getString(1));
    }
    return responseStrings;
  }

  /**
   * Execute a SELECT query, return the rows converted to the model-class objects.
   *
   * @param query     The SQL query
   * @param values    Values to replace the "?" placeholders
   * @param rowMapper Mapper which can convert a row to an object of desired model class
   * @return List of strings, returned as rows from SQL
   * @return The rows of the result table as a list of resulting objects, null on error
   */
  private <T> List<T> executeSelectQuery(String query, String[] values, RowMapper<T> rowMapper) {
    List<T> results = new LinkedList<>();

    try {
      PreparedStatement stmt = prepareStatement(query, values);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        results.add(rowMapper.rowToObject(rs));
      }
    } catch (SQLException e) {
      results = null;
    }

    return results;
  }
}
