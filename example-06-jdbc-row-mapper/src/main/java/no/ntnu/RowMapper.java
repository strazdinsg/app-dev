package no.ntnu;

import java.sql.ResultSet;

/**
 * Generic interface for converters translating an SQL ResultSet row into a model-class T object.
 *
 * @param <T> The model class, specified by implementing classes
 */
public interface RowMapper<T> {
  /**
   * Translate one SQL ResultSet row into an object of class T.
   *
   * @param resultSet ResultSet, after a successful SQL query execution.
   * @return The object of class T (model class)
   */
  T rowToObject(ResultSet resultSet);
}
