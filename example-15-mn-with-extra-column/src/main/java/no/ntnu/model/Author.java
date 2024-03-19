package no.ntnu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents an author, stored in the database.
 */
@Entity
public final class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String firstName;
  private String lastName;
  private int birthYear;

  // The mappedBy is the name of the field inside the BookAuthor class which contains a reference
  // to this object.
  // Note: it is important to annotate this with @JsonIgnore. Otherwise, there will be a recursive
  // loop when converting this to JSON
  @OneToMany(mappedBy = "author")
  @JsonIgnore
  private Set<BookAuthor> books = new HashSet<>();

  public Author() {
  }

  public Set<BookAuthor> getBooks() {
    return books;
  }

  public void setBooks(Set<BookAuthor> books) {
    this.books = books;
  }

  /**
   * Create an author.
   *
   * @param firstName First name
   * @param lastName  Last name
   * @param birthYear The year this person was born
   */
  public Author(String firstName, String lastName, int birthYear) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthYear = birthYear;
  }

  /**
   * Checks if the object is a valid author.
   *
   * @return True if it is valid, false otherwise
   */
  @JsonIgnore
  public boolean isValid() {
    return firstName != null && lastName != null && birthYear > 0;
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

}
