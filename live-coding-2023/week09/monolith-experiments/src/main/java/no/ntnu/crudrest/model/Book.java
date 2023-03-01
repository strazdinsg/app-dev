package no.ntnu.crudrest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a resource: a book. We store Book objects in the application state.
 */
@Schema(description = "A book in our library", title = "Ei bok")
@Entity
public class Book {
  @Id
  @GeneratedValue
  private int id;

  private String title;

  @Schema(description = "The year of publication, for example, 1984")
  private int year;

  private int numberOfPages;

  @ManyToMany
  @JoinTable(
      name = "author_book",
      inverseJoinColumns = @JoinColumn(name = "author_id"),
      joinColumns = @JoinColumn(name = "book_id")
  )
  private Set<Author> authors = new HashSet<>();

  public Book() {
  }

  public Book(int id, String title, int year, int numberOfPages) {
    this.id = id;
    this.title = title;
    this.year = year;
    this.numberOfPages = numberOfPages;
  }

  /**
   * Check if this object is a valid book
   *
   * @return True if the book is valid, false otherwise
   */
  @JsonIgnore
  public boolean isValid() {
    return title != null && !title.equals("");
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Returns the year of publication for the book
   *
   * @return Publication year
   */
  public int getYear() {
    return year;
  }

  /**
   * Set the year of publication for the book
   *
   * @param year Year of publication
   */
  public void setYear(int year) {
    this.year = year;
  }

  public int getNumberOfPages() {
    return numberOfPages;
  }

  public void setNumberOfPages(int numberOfPages) {
    this.numberOfPages = numberOfPages;
  }


  public Set<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(Set<Author> authors) {
    this.authors = authors;
  }
}
