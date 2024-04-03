package no.ntnu.controllers;

import no.ntnu.models.Product;
import no.ntnu.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A REST API controller serving endpoints for products.
 */
@RestController
public class ProductController {
  Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

  @Autowired
  private ProductService productService;

  /**
   * Get all products stored in the database.
   *
   * @return A list of products
   */
  @GetMapping("/api/products")
  public Iterable<Product> getAll() {
    imitateLongOperation();
    return productService.getAll();
  }

  private void imitateLongOperation() {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      logger.error("Product-loading thread interrupted!");
    }
  }
}
