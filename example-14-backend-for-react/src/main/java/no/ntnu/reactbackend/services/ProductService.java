package no.ntnu.reactbackend.services;

import java.util.HashSet;
import java.util.Set;
import no.ntnu.reactbackend.model.Product;
import org.springframework.stereotype.Service;

/**
 * Business logic for products.
 */
@Service
public class ProductService {
  Set<Product> products = new HashSet<>();

  /**
   * Get all products stored in the application.
   *
   * @return An iterable collection of all products
   */
  public Iterable<Product> getAll() {
    if (products.isEmpty()) {
      initializeFakeProducts();
    }
    return products;
  }

  /**
   * Fill product collection with fake products.
   */
  private void initializeFakeProducts() {
    products.clear();
    products.add(new Product(1, "Jeans", "Blue Jeans", 123));
    products.add(new Product(2, "Sneakers", "Regular sneakers", 226.99));
    products.add(new Product(3, "T-shirt",
        "A shirt that reminds everyone of 'green thinking'", 57));
  }
}
