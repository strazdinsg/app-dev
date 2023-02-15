package no.ntnu.crudrest;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a resource: an author. We store Author objects in the application state.
 */
public class Author {
  private int id;
  private String firstName;
  private String lastName;
  private int birthYear;

  public Author(int id, String firstName, String lastName, int birthYear) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthYear = birthYear;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getBirthYear() {
    return birthYear;
  }

  public void setBirthYear(int birthYear) {
    this.birthYear = birthYear;
  }

  /**
   * Checks if the object is a valid author
   *
   * @return True if it is valid, false otherwise
   */
  @JsonIgnore
  public boolean isValid() {
    return firstName != null && lastName != null && birthYear > 0;
  }
}
