package no.ntnu;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    /**
     * Check if the author object is valid
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
}
