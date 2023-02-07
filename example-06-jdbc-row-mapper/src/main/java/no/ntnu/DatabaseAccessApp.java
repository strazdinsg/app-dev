package no.ntnu;

import java.util.List;

/**
 * Main application entrypoint.
 */
public class DatabaseAccessApp {
  // Note: you would normally store username and password in environment files.
  // We will see how to do that later in the course.
  private static final String DB_HOST = "localhost"; // Hostname (or IP address) of the DB server
  private static final int DB_PORT = 3306; // TCP port number of the connection
  private static final String DB_DATABASE = "ddd"; // Database name
  private static final String DB_USERNAME = "root";
  private static final String DB_PASSWORD = "uc7aJFY5as";

  /**
   * Entry point for the application.
   *
   * @param args Command-line arguments, not used
   */
  public static void main(String[] args) {
    JdbcConnection connection = JdbcConnection.getInstance();
    try {
      connection.connect(DB_HOST, DB_PORT, DB_DATABASE, DB_USERNAME, DB_PASSWORD);
      System.out.println("Connection to database established");


      // Example of UPDATing data
      System.out.println("Updating borrower address...");
      int updateCount = connection.updateBorrowerAddress(1, "New address");
      System.out.println(updateCount + " borrowers updated");

      System.out.println("All books:");
      List<Book> books = connection.getAllBooks();
      for (Book book : books) {
        System.out.println("    " + book);
      }

      // Example of SELECTing data
      String bookTitle = "12 Rules For Life";
      List<String> borrowerNames = connection.getBorrowerNames(bookTitle);
      System.out.println("`" + bookTitle + "` was borrowed by:");
      for (String borrower : borrowerNames) {
        System.out.println("  * " + borrower);
      }


    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

}
