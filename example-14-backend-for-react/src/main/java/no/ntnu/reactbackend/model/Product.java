package no.ntnu.reactbackend.model;

/**
 * A product.
 *
 * @param id          Unique ID of the product
 * @param name        Name (title) of the product
 * @param description A description
 * @param price       Price of the product
 */
public record Product(int id, String name, String description, double price) {
}
