package no.ntnu.docsdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * REST controller for Product objects
 */
@RestController
@RequestMapping("products")
public class ProductController {
    // Store products here. This is for a simplicity of the demo. Normally you would load products from a database
    // and not have them cached in a static variable!
    private static final Map<Integer, Product> products = new ConcurrentHashMap<>();

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
     * @param id The ID of the product
     * @return A product or null if ID is invalid.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable int id) {
        lazyInitProducts();
        ResponseEntity<Product> response;
        Product product = products.get(id);
        if (product != null) {
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
        return new ArrayList<>(products.values());
    }

    /**
     * Add a product to the store.
     *
     * @param product The product to add
     * @return 200 OK when added, 400 on error. Also 400 when product with the same ID was present.
     */
    @PostMapping
    ResponseEntity<String> add(@RequestBody Product product) {
        lazyInitProducts();
        ResponseEntity<String> response;
        if (addProduct(product)) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    /**
     * Delete a product from the store
     *
     * @param id ID of the product to delete
     * @return 200 OK when deleted, or 404 Not found
     */
    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable int id) {
        lazyInitProducts();
        ResponseEntity<String> response;
        Product removedProduct = products.remove(id);
        if (removedProduct != null) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Lazy-initialization of the product list on first request
     */
    private void lazyInitProducts() {
        if (products.isEmpty()) {
            addProduct(new Product(1, "Soap", 12.3));
            addProduct(new Product(2, "Milk", 16.0));
            addProduct(new Product(3, "Chocolate", 28.2));
        }
    }

    /**
     * Add a product to the storage
     *
     * @param product The product to add
     * @return True when product added, false otherwise. When a product with same ID existed already, return false.
     */
    private boolean addProduct(Product product) {
        if (product != null && !products.containsKey(product.getId())) {
            products.put(product.getId(), product);
            return true;
        } else {
            return false;
        }
    }
}
