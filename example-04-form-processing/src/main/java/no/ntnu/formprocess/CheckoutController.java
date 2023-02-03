package no.ntnu.formprocess;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckoutController {

  /**
   * This endpoint parses the HTTP POST request when the form is submitted
   * - we simply collect the data and form a string with it in the response
   *
   * @param order Form data, injected (collected) in a Order object
   * @return The string to be returned to the client
   */
  @PostMapping("/checkout")
  public Order handlePost(@ModelAttribute Order order) {
    return order;
  }
}
