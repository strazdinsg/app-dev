package no.ntnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main application class, parsed by Spring Boot.
 */
@SpringBootApplication
public class SpringEnvApplication {

  /**
   * The main entrypoint of the application.
   *
   * @param args Command-line arguments, not used here
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringEnvApplication.class, args);
  }

}
