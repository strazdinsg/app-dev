package no.ntnu.repositories;

import java.util.Optional;
import no.ntnu.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repository with SQL-interface for handling User entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
}
