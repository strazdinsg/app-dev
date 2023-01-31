package no.ntnu;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data structure for storing Book data.
 *
 * @param id            Unique ID
 * @param title         Title of the book
 * @param year          The year when the book was published
 * @param numberOfPages Number of pages
 */
@Schema(description = "Represents a book in the library")
public record Book(
    @Schema(description = "Unique ID")
    int id,

    @Schema(description = "Title of the book")
    String title,

    @Schema(description = "The year when the book was published")
    int year,

    @Schema(description = "The number of pages")
    int numberOfPages
) {
}
