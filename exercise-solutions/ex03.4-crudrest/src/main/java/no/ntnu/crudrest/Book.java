package no.ntnu.crudrest;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a resource: a book. We store Book objects in the application state.
 */
public record Book(int id, String title, int year, int numberOfPages) {
  /**
   * Check if this object is a valid book.
   *
   * @return True if the book is valid, false otherwise
   */
  @JsonIgnore
  public boolean isValid() {
    return title != null && !title.equals("");
  }
}
