package no.ntnu.docsdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     *
     * @return Number of products currently in the store
     */
    @GetMapping("/count")
    public int getCount() {
        lazyInitProducts();
        return products.size();
    }

    /**
     * Get a specific product
     *
     * @param index The index of the product in the store. Indexing starts at 0.
     * @return A product or null if index is invalid.
     */
    @GetMapping("/get")
    public ResponseEntity<Product> get(int index) {
        lazyInitProducts();
        ResponseEntity<Product> response;
        if (index >= 0 && index < products.size()) {
            Product product = products.get(index);
            response = new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Get all products in the store.
     *
     * @return List of all products
     */
    @GetMapping
    public List<Product> getAll() {
        lazyInitProducts();
        return products;
    }

    /**
     * Add a product to the store.
     *
     * @param product The product to add
     * @return 200 OK when added, 400 on error
     */
    @PostMapping
    ResponseEntity add(@RequestBody Product product) {
        lazyInitProducts();
        ResponseEntity response;
        if (product != null) {
            products.add(product);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            response = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    /**
     * Delete a product from the store
     *
     * @param index Index of the product to delete. Indexing starts at 0.
     * @return 200 OK when deleted, or 404 Not found
     */
    @DeleteMapping
    ResponseEntity delete(int index) {
        lazyInitProducts();
        ResponseEntity response;
        if (index >= 0 && index < products.size()) {
            products.remove(index);
            response = new ResponseEntity(HttpStatus.OK);
        } else {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return response;
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
