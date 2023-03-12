package no.ntnu.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A REST API controller which responds to HTTP requests.
 */
@RestController
public class GreetingController {

    /**
     * This method is called when HTTP GET /api/hello is received
     *
     * @return The message to be sent in the HTTP response's body
     */
    @GetMapping("/api/hello")
    public String greeting() {
        return "Hello, World";
    }
}

