import java.util.Map;

/**
 * An example application showing how we can read environment variables in Java
 */
public class EnvExample {
    public static void main(String[] args) {
        System.out.println("Your JAVA_HOME environment variable is " + System.getenv("JAVA_HOME"));
        Map<String, String> environment = System.getenv();
        System.out.println("All your environment variables:");
        for (String variableName : environment.keySet()) {
            System.out.println(variableName + "\n  " + environment.get(variableName));
        }
    }
}

