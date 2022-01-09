package no.ntnu.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A REST API controller which responds to HTTP requests for /hello
 */
@RestController
public class GreetingController {

    @GetMapping("/hello")
    public String greeting() {
        return "Hello, World";
    }
}

