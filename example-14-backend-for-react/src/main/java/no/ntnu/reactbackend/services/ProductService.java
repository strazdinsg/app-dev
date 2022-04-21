package no.ntnu.reactbackend.services;

import no.ntnu.reactbackend.model.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Business logic for products
 */
@Service
public class ProductService {
    Set<Product> products = new HashSet<>();

    public Collection<Product> getAll() {
        if (products.isEmpty()) {
            initializeFakeProducts();
        }
        return products;
    }

    /**
     * Fill product collection with fake products
     */
    private void initializeFakeProducts() {
        // TODO
    }
}
