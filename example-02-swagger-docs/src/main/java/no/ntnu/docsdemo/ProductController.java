package no.ntnu.docsdemo;

import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * REST controller for Product objects
 */
@RestController
@RequestMapping("products")
public class ProductController {
    // Store products here. This is for a simplicity of the demo. Normally you would load products from a database
    // and not have them cached in a static variable!
    private static final List<Product> products = new LinkedList<>();

    /**
     * Get number of products in the store
     * @return Number of products currently in the store
     */
    @GetMapping("/count")
    public int getCount() {
        lazyInitProducts();
        return products.size();
    }

    /**
     * Get a specific product
     * @param index The index of the product in the store. Indexing starts at 0.
     * @return A product or null if index is invalid.
     */
    @GetMapping("/get")
    public Product get(int index) {
        lazyInitProducts();
        return index >= 0 && index < products.size() ? products.get(index) : null;
    }

    /**
     * Get all procuts in the store.
     * @return
     */
    @GetMapping
    public List<Product> getAll() {
        lazyInitProducts();
        return products;
    }

    /**
     * Add a product to the store.
     * @param product The product to add
     * @return True when product successfully added, false otherwise.
     */
    @PostMapping
    boolean add(Product product) {
        lazyInitProducts();
        boolean success = false;
        if (product != null) {
            products.add(product);
            success = true;
        }
        return success;
    }

    /**
     * Delete a product from the store
     * @param index Index of the product to delete. Indexing starts at 0.
     * @return True when deleted, false when no product was deleted.
     */
    @DeleteMapping
    boolean delete(int index) {
        lazyInitProducts();
        boolean success = false;
        if (index >= 0 && index < products.size()) {
            products.remove(index);
            success = true;
        }
        return success;
    }

    /**
     * Lazy-initialization of the product list on first request
     */
    private void lazyInitProducts() {
        if (products.isEmpty()) {
            products.add(new Product("Soap", 12.3));
            products.add(new Product("Milk", 16.0));
            products.add(new Product("Chocolate", 28.2));
        }
    }
}
