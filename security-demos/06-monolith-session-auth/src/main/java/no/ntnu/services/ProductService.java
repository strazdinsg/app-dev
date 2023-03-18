package no.ntnu.services;

import no.ntnu.models.Product;
import no.ntnu.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Business logic for products
 */
@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  public List<Product> getAll() {
    List<Product> products = new LinkedList<>();
    productRepository.findAll().forEach(products::add);
    return products;
  }
}
