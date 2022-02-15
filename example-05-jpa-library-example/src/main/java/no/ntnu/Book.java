package no.ntnu;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

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
    @JsonIgnore
    private Genre genre;

    public Book() {}

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

    /**
     * Check if the book object is valid
     * @return True when valid, false when invalid
     */
    // The JsonIgnore annotation makes sure we don't include the "valid" field when generating JSON
    @JsonIgnore
    public boolean isValid() {
        return (id == null || id > 0) && !"".equals(title) && yearIssued > 0 && numberOfPages > 0;
    }
}
