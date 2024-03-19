package no.ntnu.repositories;

import no.ntnu.model.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for handling data storage in SQL.
 */
@Repository
public interface LoanRepository extends CrudRepository<Loan, Integer> {
}
