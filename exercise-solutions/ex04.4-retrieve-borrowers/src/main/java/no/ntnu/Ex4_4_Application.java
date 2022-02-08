package no.ntnu;

import java.util.List;

/**
 * Main application entrypoint
 */
public class Ex4_4_Application {
    public static void main(String[] args) {
        JdbcConnection connection = JdbcConnection.getInstance();
        try {
            connection.connect("localhost", 3306, "library", "myuser", "secretpassword");
            String bookTitle = "12 Rules For Life";
            List<String> borrowerNames = connection.getBorrowerNames(bookTitle);
            System.out.println("bookTitle was borrowed by:");
            for (String borrower: borrowerNames) {
                System.out.println("    * " + borrower);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
