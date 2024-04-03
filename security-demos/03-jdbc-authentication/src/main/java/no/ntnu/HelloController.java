package no.ntnu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A simple REST API controller providing different endpoints.
 */
@RestController
public class HelloController {
  @GetMapping("/")
  public String home() {
    return "This is a public home page";
  }

  @GetMapping("user")
  public String userPage() {
    return "This is accessible to all authorized users";
  }

  @GetMapping("admin")
  public String adminPage() {
    return "This is accessible only for ADMIN users";
  }
}
