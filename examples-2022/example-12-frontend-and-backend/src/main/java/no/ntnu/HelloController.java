package no.ntnu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a REST API controller - part of the backend
 */
@RestController
// Instead of specifying /api here, we re-use a constant (environment variable) for the base path
// This makes it possible to later change base path of all controllers by changing a single value in a config file
@RequestMapping("${spring.data.rest.basePath}")
public class HelloController {

    /**
     * Return a simple "Hello world" as a response to HTTP GET /api/hello
     * @return The hello world text
     */
    @GetMapping("hello")
    public String greet() {
        return "Hello world";
    }
}
