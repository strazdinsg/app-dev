package no.ntnu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a resource: an author. We store Author objects in the application state (database).
 */
@Entity
public class Author {
  @Id
  @GeneratedValue
  private int id;
  private String firstName;
  private String lastName;
  private int birthYear;
  @ManyToMany
  @JoinTable(name = "author_book",
      joinColumns = @JoinColumn(name = "author_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id")
  )
  @JsonIgnore
  private Set<Book> books = new HashSet<>();

  public Author() {
  }

  /**
   * Create an author.
   *
   * @param firstName First name
   * @param lastName  Last name
   * @param birthYear Year of birth (For example, 1980)
   */
  public Author(String firstName, String lastName, int birthYear) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthYear = birthYear;
  }

  /**
   * Check if the author object is valid.
   *
   * @return True when valid, false when not
   */
  @JsonIgnore // This annotation makes sure we don't include "valid" in the JSON
  public boolean isValid() {
    return !"".equals(firstName) && !"".equals(lastName) && birthYear > 0;
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

  public Set<Book> getBooks() {
    return books;
  }

  public void setBooks(Set<Book> books) {
    this.books = books;
  }

  /**
   * Add a book to the author's book collection.
   *
   * @param book The book to add
   */
  public void addBook(Book book) {
    books.add(book);
  }
}
