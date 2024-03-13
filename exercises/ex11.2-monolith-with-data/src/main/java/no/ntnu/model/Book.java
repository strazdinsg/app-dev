package no.ntnu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a resource: a book. We store Book objects in the application state (database).
 */
@Entity
public class Book {
  @Id
  @GeneratedValue
  private Integer id;
  private String title;
  private int yearIssued;
  private int numberOfPages;

  @ManyToOne
  private Genre genre;

  @ManyToMany(mappedBy = "books")
  private Set<Author> authors = new HashSet<>();

  /**
   * Create a book. Default constructor is needed for JPA.
   */
  public Book() {
  }

  /**
   * Create a book.
   *
   * @param title         Title of the book
   * @param yearIssued    The year when the book was issued
   * @param numberOfPages The number of pages
   * @param genre         The main genre of the book
   */
  public Book(String title, int yearIssued, int numberOfPages, Genre genre) {
    this.title = title;
    this.yearIssued = yearIssued;
    this.numberOfPages = numberOfPages;
    this.genre = genre;
  }

  public Genre getGenre() {
    return genre;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getYearIssued() {
    return yearIssued;
  }

  public void setYearIssued(int year) {
    this.yearIssued = year;
  }

  public int getNumberOfPages() {
    return numberOfPages;
  }

  public void setNumberOfPages(int numberOfPages) {
    this.numberOfPages = numberOfPages;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public Set<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(Set<Author> authors) {
    this.authors = authors;
  }

  /**
   * Check if the book object is valid.
   *
   * @return True when valid, false when invalid
   */
  // The JsonIgnore annotation makes sure we don't include the "valid" field when generating JSON
  @JsonIgnore
  public boolean isValid() {
    return (id == null || id > 0) && !"".equals(title) && yearIssued > 0 && numberOfPages > 0;
  }

  /**
   * Add an author to the author list of the book.
   *
   * @param author The author to add
   */
  public void addAuthor(Author author) {
    authors.add(author);
  }
}
