package no.ntnu.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class of the application.
 */
@SpringBootApplication
public class SimpleOauthGithubApplication {

  /**
   * Starts the application.
   *
   * @param args The command line arguments.
   */
  public static void main(String[] args) {
    SpringApplication.run(SimpleOauthGithubApplication.class, args);
  }

}
