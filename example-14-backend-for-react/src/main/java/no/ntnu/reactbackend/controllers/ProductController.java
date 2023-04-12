package no.ntnu.reactbackend.controllers;

import no.ntnu.reactbackend.model.Product;
import no.ntnu.reactbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Serves product-related REST API endpoints.
 */
@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
  @Autowired
  private ProductService productService;

  /**
   * Get All products stored in the database.
   *
   * @return All products in the storage
   * @throws InterruptedException An exception if the executing thread is interrupted while
   *                              it is sleeping
   */
  @GetMapping
  public Iterable<Product> getAll() throws InterruptedException {
    // Imitate a long operation
    Thread.sleep(2000);
    return productService.getAll();
  }
}
