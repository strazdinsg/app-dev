package no.ntnu;

/**
 * Data structure for storing author data.
 *
 * @param id        Unique ID
 * @param firstName First name
 * @param lastName  Last name
 * @param birthYear Year of birth (1990, etc)
 */
public record Author(int id, String firstName, String lastName, int birthYear) {
}
