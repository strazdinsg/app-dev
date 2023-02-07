package no.ntnu;

/**
 * A model class representing a book.
 */
public class Book {
  private int id;
  private String title;
  private int year;

  public Book() {
  }

  /**
   * Create a new book.
   *
   * @param id    Unique ID for the book.
   * @param title The title of the book
   * @param year  Publication year (for example, 2023)
   */
  public Book(int id, String title, int year) {
    this.id = id;
    this.title = title;
    this.year = year;
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

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  @Override
  public String toString() {
    return "Book{id=" + id + ", title='" + title + '\'' + ", year=" + year + '}';
  }
}
