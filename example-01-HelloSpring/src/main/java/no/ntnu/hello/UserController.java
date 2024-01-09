package no.ntnu.hello;

import java.util.LinkedList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A REST API controller which responds to HTTP requests for /users.
 */
@RestController
@RequestMapping("users")
public class UserController {
  /**
   * This method is called when an HTTP request to /users/list is received.
   * P.S. We need to specify only /list as the path (URL part) here, because the
   * controller is already mapped to /users
   *
   * @return A list of users
   */
  @GetMapping("/list")
  public List<User> listUsers() {
    List<User> users = new LinkedList<>();
    users.add(new User("Chuck", "Dorris"));
    users.add(new User("Ada", "Lovelace"));
    return users;
  }
}
