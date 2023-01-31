package no.ntnu.testingdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Integration tests, uses MockMvc.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class DemoApplicationTests {

  /**
   * Used for simulating API endpoint requests and analyzing them
   */
  @Autowired
  MockMvc mvc;

  /**
   * Used for converting Hello <-> JSON string
   */
  @Autowired
  ObjectMapper objectMapper;

  /**
   * This test simply checks if we manage to compile and initialize the application without any errors
   */
  @Test
  void contextLoads() {
  }

  /**
   * Check if GET /hello returns a string containing word "world" and status code 200 OK
   *
   * @throws Exception MVC mock can throw an exception
   */
  @Test
  void greetingTextCheck() throws Exception {
    mvc.perform(get("/hello"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("world")));
  }

  /**
   * Check if DELETE /delete returns "Not available" in the response body and status code 501 Not implemented
   *
   * @throws Exception MVC mock can throw an exception
   */
  @Test
  void deleteCheck() throws Exception {
    mvc.perform(delete("/delete"))
        .andExpect(status().isNotImplemented())
        .andExpect(content().string(equalTo("Not available")));
    ;
  }

  /**
   * Check if GET /hello/object returns the expected Hello object and code 200 OK
   *
   * @throws Exception MVC mock can throw an exception
   */
  @Test
  void checkResponseObject() throws Exception {
    String responseJson = mvc.perform(get("/hello/object"))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();

    Hello hello = convertJsonToHello(responseJson);

    assertThat(hello.getTitle()).isEqualTo("Hello");
    assertThat(hello.getMessage()).contains("Mars");
  }

  private Hello convertJsonToHello(String responseJson) throws JsonProcessingException {
    return objectMapper.readValue(responseJson, Hello.class);
  }

  /**
   * Perform an HTTP POST with JSON data in the request body. Check if return code is 201 CREATED.
   *
   * @throws Exception JSON parsing or MockMvc can throw an exception
   */
  @Test
  void tryPost() throws Exception {
    String jsonString = convertHelloToJson(new Hello("Hei", "Hei på deg"));
    mvc.perform(post("/add")
            .content(jsonString)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  private String convertHelloToJson(Hello hello) throws JsonProcessingException {
    return objectMapper.writeValueAsString(hello);
  }

  /**
   * Perform an HTTP POST without any data. The response code must be BAD REQUEST.
   *
   * @throws Exception MockMvc can throw an exception
   */
  @Test
  void tryEmptyPost() throws Exception {
    mvc.perform(post("/add")
            .content("{}")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotAcceptable());
  }
}
