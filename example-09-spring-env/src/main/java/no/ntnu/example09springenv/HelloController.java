package no.ntnu.example09springenv;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    /** This variable will contain the value of environment variable JAVA_HOME - Spring boot will inject it */
    @Value("${JAVA_HOME}")
    private String javaPath;

    /**
     * Respond to HTTP GET / request with the value of JAVA_HOME environment variable
     * @return Value of JAVA_HOME environment variable
     */
    @GetMapping
    public String getJavaPathVar() {
        return javaPath;
    }
}
