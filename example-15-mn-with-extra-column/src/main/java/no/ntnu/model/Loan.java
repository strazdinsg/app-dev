package no.ntnu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * Represents a loan where a borrower borrows a single book from the library, for a limited time
 * period.
 */
@Entity
public class Loan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @ManyToOne
  @JoinColumn(name = "book_id")
  private Book book;
  @ManyToOne
  @JoinColumn(name = "borrower_email")
  private Borrower borrower;
  private LocalDate fromDate;
  private LocalDate toDate;

  public Loan() {
  }

  /**
   * Create a loan.
   *
   * @param book     The book that was borrowed
   * @param borrower The person who borrowed the book
   * @param fromDate Start date for the loan
   * @param toDate   End date for the loan
   */
  public Loan(Book book, Borrower borrower, LocalDate fromDate, LocalDate toDate) {
    this.book = book;
    this.borrower = borrower;
    this.fromDate = fromDate;
    this.toDate = toDate;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public Borrower getBorrower() {
    return borrower;
  }

  public void setBorrower(Borrower borrower) {
    this.borrower = borrower;
  }

  public LocalDate getFromDate() {
    return fromDate;
  }

  public void setFromDate(LocalDate fromDate) {
    this.fromDate = fromDate;
  }

  public LocalDate getToDate() {
    return toDate;
  }

  public void setToDate(LocalDate toDate) {
    this.toDate = toDate;
  }
}
