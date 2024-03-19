package no.ntnu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a person who borrows books at the library.
 */
@Entity
public class Borrower {
  @Id
  private String email;
  private String firstName;
  private String lastName;
  private String address;

  @OneToMany(mappedBy = "borrower")
  @JsonIgnore
  private Set<Loan> loans = new HashSet<>();

  /**
   * Create a borrower.
   *
   * @param email     Email address (account identifier)
   * @param firstName First name
   * @param lastName  Last name
   * @param address   Home address
   */
  public Borrower(String email, String firstName, String lastName, String address) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
  }

  public Borrower() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Set<Loan> getLoans() {
    return loans;
  }

  public void setLoans(Set<Loan> loans) {
    this.loans = loans;
  }
}
