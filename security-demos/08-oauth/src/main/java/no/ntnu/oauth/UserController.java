package no.ntnu.oauth;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the user endpoints.
 */
@RestController
public class UserController {
  /**
   * Get the current user.
   *
   * @param principal The current user principal, automatically injected by Spring Security
   * @return The name of the current user
   */
  @GetMapping("/user")
  public String getUser(@AuthenticationPrincipal OAuth2User principal) {
    return principal.getAttribute("name");
  }
}
