package no.ntnu;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * REST API, for debugging.
 */
@RestController
public class HelloController {
  /* We can get the whole config object, values from .yml injected in it */
  @Autowired
  private Config config;
  /* We can still access individual variables from the .yml file */
  @Value("${myapp.use-auth}")
  private boolean useAuth;

  /**
   * Return a JSON object as a response to HTTP GET /.
   *
   * @return A JSON object showing loaded environment variables
   */
  @GetMapping("/")
  public Map<String, Object> hello() {
    Map<String, Object> variables = new HashMap<>();
    variables.put("servers", config.getServers());
    variables.put("port", config.getPort());
    variables.put("useAuth", useAuth);
    return variables;
  }
}
