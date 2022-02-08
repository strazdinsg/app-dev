package no.ntnu;

/**
 * Main application entrypoint
 */
public class Ex4_1_Application {
    public static void main(String[] args) {
        JdbcConnection connection = JdbcConnection.getInstance();
        try {
            connection.connect("localhost", 3306, "library", "myuser", "secretpassword");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String status = connection.isConnected() ? "established" : "failed";
        System.out.println("Connection " + status);
    }

}
