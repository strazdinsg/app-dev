package no.ntnu;


import no.ntnu.models.Role;
import no.ntnu.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for User class.
 */
public class UserTest {

  @Test
  public void testRoleCheck() {
    Role userRole = new Role("ROLE_USER");
    Role adminRole = new Role("ROLE_ADMIN");

    User regularUser = new User("regular", "fakePassword");
    regularUser.addRole(userRole);

    User adminUser = new User("admin", "fakePassword");
    adminUser.addRole(userRole);
    adminUser.addRole(adminRole);

    assertFalse(regularUser.isAdmin());
    assertFalse(regularUser.hasRole("ROLE_ADMIN"));
    assertTrue(regularUser.hasRole("ROLE_USER"));

    assertTrue(adminUser.isAdmin());
    assertTrue(adminUser.hasRole("ROLE_ADMIN"));
    assertTrue(adminUser.hasRole("ROLE_USER"));
  }
}
