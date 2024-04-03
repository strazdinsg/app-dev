package no.ntnu.repositories;

import no.ntnu.models.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for sending queries and statements to product table in the SQL database.
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
