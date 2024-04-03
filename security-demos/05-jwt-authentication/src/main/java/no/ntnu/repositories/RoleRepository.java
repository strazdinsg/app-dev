package no.ntnu.repositories;

import no.ntnu.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for SQL-handling of Role entities.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
