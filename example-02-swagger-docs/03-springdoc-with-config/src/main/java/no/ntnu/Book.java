package no.ntnu;

/**
 * Data structure for storing Book data.
 *
 * @param id            Unique ID
 * @param title         Title of the book
 * @param year          The year when the book was published
 * @param numberOfPages Number of pages
 */
public record Book(int id, String title, int year, int numberOfPages) {
}
