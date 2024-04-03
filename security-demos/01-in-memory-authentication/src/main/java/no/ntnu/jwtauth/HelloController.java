package no.ntnu.jwtauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple REST API controller.
 */
@RestController
public class HelloController {
  @GetMapping("/")
  public String home() {
    return "This is accessible only to authorized users";
  }
}
