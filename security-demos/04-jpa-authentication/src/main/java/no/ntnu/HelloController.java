package no.ntnu;

import no.ntnu.security.AccessUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A simple REST API controller providing different endpoints
 */
@RestController
public class HelloController {
  @GetMapping("")
  public String home() {
    return "This is a public home page";
  }

  @GetMapping("user")
  public String userPage(@AuthenticationPrincipal AccessUserDetails loggedInUser) {
    return "You are currently logged in as " + loggedInUser.getUsername();
  }

  @GetMapping("admin")
  public String adminPage() {
    // Here we use another way to get reference to currently logged in user
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication auth = securityContext.getAuthentication();
    String username = auth.getName();
    return "You are logged in as ADMIN user: " + username;
  }
}
