package no.ntnu;

import java.util.Map;

/**
 * An example application showing how we can read environment variables in Java.
 */
public class EnvExample {
  /**
   * Entrypoint of the application.
   *
   * @param args Command-line arguments, not used
   */
  public static void main(String[] args) {
    System.out.println("Your JAVA_HOME environment variable is " + System.getenv("JAVA_HOME"));
    Map<String, String> environment = System.getenv();
    System.out.println("All your environment variables:");
    for (Map.Entry<String, String> e : environment.entrySet()) {
      System.out.println(e.getKey() + "\n  " + e.getValue());
    }
  }
}

