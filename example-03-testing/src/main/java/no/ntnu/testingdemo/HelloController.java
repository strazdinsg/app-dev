package no.ntnu.testingdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API controller, with some dummy endpoints, for testing.
 */
@RestController
public class HelloController {

  /**
   * Return a simple message to HTTP GET /hello .
   *
   * @return A simple mess
   */
  @GetMapping("/hello")
  public String greeting() {
    return "Hello, world!";
  }

  /**
   * Return code 501 NOT IMPLEMENTED on HTTP DELETE /delete .
   *
   * @return Response with status code 501
   */
  @DeleteMapping("/delete")
  public ResponseEntity<String> invalidDelete() {
    return new ResponseEntity<>("Not available", HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * Return a Hello object, encoded as JSON string to HTTP GET /hello/object .
   *
   * @return Hello object (JSON)
   */
  @GetMapping("/hello/object")
  public Hello getHelloObject() {
    return new Hello("Hello", "Greetings from Mars!");
  }

  /**
   * Simulate addition of a new object on HTTP POST /add .
   *
   * @param hello The hello object to add, received from HTTP request body, JSON string
   * @return Status code 201 CREATED when hello is not null, 406 NOT ACCEPTABLE otherwise
   */
  @PostMapping("/add")
  public ResponseEntity<String> add(@RequestBody Hello hello) {
    HttpStatus status = hello.isValid() ? HttpStatus.CREATED : HttpStatus.NOT_ACCEPTABLE;
    return new ResponseEntity<>(status);
  }
}
