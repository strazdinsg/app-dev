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
 * Represents a resource: a book. We store Book objects in the application state.
 */
@Entity
public final class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String title;
  private int publicationYear;
  private int numberOfPages;

  @OneToMany(mappedBy = "book")
  private Set<BookAuthor> authors = new HashSet<>();

  public Book() {
  }

  /**
   * Create a book.
   *
   * @param title           Title of the book
   * @param publicationYear Publication year
   * @param numberOfPages   Number of pages
   */
  public Book(String title, int publicationYear, int numberOfPages) {
    this.title = title;
    this.publicationYear = publicationYear;
    this.numberOfPages = numberOfPages;
  }

  /**
   * Check if this object is a valid book.
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

  public int getPublicationYear() {
    return publicationYear;
  }

  public void setPublicationYear(int year) {
    this.publicationYear = year;
  }

  public int getNumberOfPages() {
    return numberOfPages;
  }

  public void setNumberOfPages(int numberOfPages) {
    this.numberOfPages = numberOfPages;
  }

  public Set<BookAuthor> getAuthors() {
    return authors;
  }

  public void setAuthors(Set<BookAuthor> authors) {
    this.authors = authors;
  }

}
