package no.ntnu;


import no.ntnu.models.Role;
import no.ntnu.models.User;
import no.ntnu.security.AccessUserDetails;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for AccessUserDetails class
 */
public class AccessUserDetailsTest {

    @Test
    public void testRoleCheck() {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");

        User regularUser = new User("regular", "fakePassword");
        regularUser.addRole(userRole);

        User adminUser = new User("admin", "fakePassword");
        adminUser.addRole(userRole);
        adminUser.addRole(adminRole);

        AccessUserDetails regularUserDetails = new AccessUserDetails(regularUser);
        assertFalse(regularUserDetails.isAdmin());
        assertFalse(regularUserDetails.hasRole("ROLE_ADMIN"));
        assertTrue(regularUserDetails.hasRole("ROLE_USER"));

        AccessUserDetails adminUserDetails = new AccessUserDetails(adminUser);
        assertTrue(adminUserDetails.isAdmin());
        assertTrue(adminUserDetails.hasRole("ROLE_ADMIN"));
        assertTrue(adminUserDetails.hasRole("ROLE_USER"));
    }
}
