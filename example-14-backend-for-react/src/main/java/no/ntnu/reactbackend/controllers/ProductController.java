package no.ntnu.reactbackend.controllers;

import no.ntnu.reactbackend.model.Product;
import no.ntnu.reactbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Serves product-related REST API endpoints
 */
@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * Get All products stored in the database
     *
     * @return All products in the storage
     */
    @GetMapping
    public Collection<Product> getAll() {
        return productService.getAll();
    }
}
