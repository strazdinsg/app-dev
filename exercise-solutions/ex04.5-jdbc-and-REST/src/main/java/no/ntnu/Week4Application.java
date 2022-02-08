package no.ntnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Week4Application {
    // TODO - this is the incorrect way - constants inside the code! We will move it to application.properties!
    static final String DB_HOST = "localhost";
    static final int DB_PORT = 3306;
    static final String DB_NAME = "library";
    static final String DB_USER = "myuser";
    static final String DB_PASSWORD = "secretpassword";

    public static void main(String[] args) {
        SpringApplication.run(Week4Application.class, args);
    }

}
