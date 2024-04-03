package no.ntnu.services;

import no.ntnu.models.Product;
import no.ntnu.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic for products.
 */
@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  /**
   * Get all the products stored in the database.
   *
   * @return List of all the products
   */
  public Iterable<Product> getAll() {
    return productRepository.findAll();
  }
}
