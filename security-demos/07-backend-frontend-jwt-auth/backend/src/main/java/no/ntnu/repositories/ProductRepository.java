package no.ntnu.repositories;

import no.ntnu.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for sending queries and statements to product table in the SQL database.
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
