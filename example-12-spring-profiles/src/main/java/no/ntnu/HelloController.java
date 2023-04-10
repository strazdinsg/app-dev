package no.ntnu;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API controller class for getting the environment variables.
 */
@RestController
public class HelloController {
  /**
   * This variable will contain the value of environment variable JAVA_HOME -
   * Spring boot will inject it.
   */
  @Value("${spring.datasource.url}")
  private String dbUrl;

  /**
   * Respond to HTTP GET / request with values of some environment variables.
   *
   * @return A Map (will be converted to JSON object) containing the variables
   */
  @GetMapping("/")
  public Map<String, Object> getEnvVariables() {
    Map<String, Object> env = new HashMap<>();
    env.put("DB_URL", dbUrl);
    return env;
  }
}
