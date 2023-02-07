package no.ntnu;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A converter - can convert one row os SQL ResultSet into a Book object.
 */
public class BookRowMapper implements RowMapper<Book> {
  @Override
  public Book rowToObject(ResultSet rs) {
    try {
      return new Book(rs.getInt("id"), rs.getString("title"), rs.getInt("year"));
    } catch (SQLException e) {
      System.err.println("Error while converting SQL row to book: " + e.getMessage());
      return null;
    }
  }
}
