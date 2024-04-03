package no.ntnu.repositories;

import no.ntnu.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repository class for handling SQL-interface for Role entities.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
