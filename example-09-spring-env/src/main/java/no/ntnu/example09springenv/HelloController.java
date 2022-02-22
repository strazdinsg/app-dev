package no.ntnu.example09springenv;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {
    /** This variable will contain the value of environment variable JAVA_HOME - Spring boot will inject it */
    @Value("${JAVA_HOME}")
    private String javaPath;
    @Value("${MY_ENV_VARIABLE}")
    private String myCustomVariable;
    @Value("${MY_INT_PARAM}")
    private Integer myIntParam;


    /**
     * Respond to HTTP GET / request with values of some environment variables
     * @return A Map (will be converted to JSON object) containing the variables
     */
    @GetMapping
    public Map<String, Object> getEnvVariables() {
        Map<String, Object> env = new HashMap<>();
        env.put("JAVA_HOME", javaPath);
        env.put("MY_ENV_VARIABLE", myCustomVariable);
        env.put("my integer parameter", myIntParam);
        return env;
    }
}
