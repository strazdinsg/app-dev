package no.ntnu.crudrest;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

/**
 * Represents a resource: an author. We store Author objects in the application state.
 */
@Entity
public final class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String firstName;
  private String lastName;
  private int birthYear;

  @ManyToMany
  @JoinTable(
      name="author_book",
      joinColumns = @JoinColumn(name="author_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id")
  )
  @JsonIgnore
  private Set<Book> books = new HashSet<>();

  public Author() {}

  public Set<Book> getBooks() {
    return books;
  }

  public void setBooks(Set<Book> books) {
    this.books = books;
  }

  /**
   * @param id        Unique ID
   * @param firstName First name
   * @param lastName  Last name
   * @param birthYear The year this person was born
   */
  public Author(int id, String firstName, String lastName, int birthYear) {
    this.id = id;
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

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (Author) obj;
    return this.id == that.id &&
        Objects.equals(this.firstName, that.firstName) &&
        Objects.equals(this.lastName, that.lastName) &&
        this.birthYear == that.birthYear;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, birthYear);
  }

  @Override
  public String toString() {
    return "Author[" +
        "id=" + id + ", " +
        "firstName=" + firstName + ", " +
        "lastName=" + lastName + ", " +
        "birthYear=" + birthYear + ']';
  }

}
