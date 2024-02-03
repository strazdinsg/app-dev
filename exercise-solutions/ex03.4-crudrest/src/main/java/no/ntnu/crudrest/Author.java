package no.ntnu.crudrest;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a resource: an author. We store Author objects in the application state.
 *
 * @param id        Unique ID
 * @param firstName First name
 * @param lastName  Last name
 * @param birthYear The year this person was born
 */
public record Author(int id, String firstName, String lastName, int birthYear) {

  /**
   * Checks if the object is a valid author.
   *
   * @return True if it is valid, false otherwise
   */
  @JsonIgnore
  public boolean isValid() {
    return firstName != null && lastName != null && birthYear > 0;
  }
}
