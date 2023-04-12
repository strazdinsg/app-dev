package no.ntnu.reactbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class.
 */
@SpringBootApplication
public class ReactBackendApplication {

  /**
   * The entry-point of the application.
   *
   * @param args Command-line arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(ReactBackendApplication.class, args);
  }
}
