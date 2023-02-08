package no.ntnu.crudrest;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Handle SQL database access, for books.
 */
@Component
public class BookRepository {
  Connection sqlConnection;

  /**
   * Get all the books from the database.
   *
   * @return A list of books or null on error
   */
  public Collection<Book> getAll() {
    return selectBooksByQuery("SELECT * FROM books");
  }

  /**
   * Select a single book from the database.
   *
   * @param id The ID of the book to select
   * @return The book or null on error
   */
  public Book findBookById(int id) {
    if (!establishConnectionToSql()) {
      return null;
    }
    ResultSet resultSet = executeQuery("SELECT * FROM books WHERE id = ?",
        new String[]{"" + id});
    Book book = null;
    try {
      if (resultSet != null && resultSet.next()) {
        book = parseOneResultRow(resultSet);
      }
    } catch (SQLException e) {
      System.out.println("Error while parsing the book result row: " + e.getMessage());
    }

    return book;
  }

  private Collection<Book> selectBooksByQuery(String query) {
    if (!establishConnectionToSql()) {
      return null;
    }

    ResultSet resultSet = executeQuery(query, null);
    if (resultSet == null) return null;

    return parseBooksResultSet(resultSet);
  }

  private ResultSet executeQuery(String query, String[] parameters) {
    ResultSet resultSet;
    try {
      PreparedStatement statement = sqlConnection.prepareStatement(query);
      if (parameters != null) {
        setQueryParameters(statement, parameters);
      }
      resultSet = statement.executeQuery();
    } catch (SQLException e) {
      System.out.println("Could not execute the query: " + e.getMessage());
      return null;
    }
    return resultSet;
  }

  private void setQueryParameters(PreparedStatement statement, String[] parameters)
      throws SQLException {
    for (int i = 0; i < parameters.length; ++i) {
      statement.setString(i + 1, parameters[i]);
    }
  }

  private static List<Book> parseBooksResultSet(ResultSet resultSet) {
    List<Book> books = new LinkedList<>();

    try {
      while (resultSet.next()) {
        Book book = parseOneResultRow(resultSet);
        books.add(book);
      }
    } catch (SQLException e) {
      System.out.println("Error while parsing SQL result set: " + e.getMessage());
      books = null;
    }

    return books;
  }

  private static Book parseOneResultRow(ResultSet resultSet) throws SQLException {
    return new Book(
        resultSet.getInt("id"),
        resultSet.getString("title"),
        resultSet.getInt("year"),
        resultSet.getInt("number_of_pages")
    );
  }

  private boolean establishConnectionToSql() {
    if (sqlConnection != null) {
      return true;
    }

    try {
      sqlConnection = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/library?user=libuser&password=libuser23!");
      System.out.println("Connection established");
    } catch (SQLException e) {
      System.out.println("Could not connect to database: " + e.getMessage());
    }

    return sqlConnection != null;
  }

}
