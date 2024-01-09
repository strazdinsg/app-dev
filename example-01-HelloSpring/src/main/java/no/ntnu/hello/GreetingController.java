package no.ntnu.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A REST API controller which responds to HTTP requests for /hello.
 */
@RestController
public class GreetingController {

  /**
   * Responds to HTTP GET requests for /hello.
   *
   * @return a greeting message
   */
  @GetMapping("/hello")
  public String greeting() {
    return "Hello, World!";
  }
}

