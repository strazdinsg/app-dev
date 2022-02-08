package no.ntnu;

/**
 * Main application entrypoint
 */
public class Ex4_3_Application {
    public static void main(String[] args) {
        JdbcConnection connection = JdbcConnection.getInstance();
        try {
            connection.connect("localhost", 3306, "library", "myuser", "secretpassword");
            connection.updateBorrowerAddress(3, "Larsgardsvegen 2");
            System.out.println("All went well, congrats!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
