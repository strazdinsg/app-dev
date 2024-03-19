package no.ntnu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Represents a book-author relationship.
 */
@Entity
public class BookAuthor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  // Note: it is important to annotate this with @JsonIgnore. Otherwise, there will be a recursive
  // loop when converting this to JSON
  @ManyToOne
  @JoinColumn(name = "book_id")
  @JsonIgnore
  private Book book;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private Author author;

  private String role;

  public BookAuthor() {
  }

  /**
   * Create a book-author relation.
   *
   * @param book   The authored book
   * @param author One of the authors for the book
   * @param role   The role of the author. It can be an author, editor, translator, etc.
   */
  public BookAuthor(Book book, Author author, String role) {
    this.book = book;
    this.author = author;
    this.role = role;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

}
