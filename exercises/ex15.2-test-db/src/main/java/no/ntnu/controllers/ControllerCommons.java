package no.ntnu.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Some common operations shared among controllers.
 */
public class ControllerCommons {
  /**
   * Not allowed to instantiate the class.
   */
  private ControllerCommons() {

  }

  /**
   * Create an HTTP Response entity with either OK or BAD REQUEST status.
   *
   * @param errorMessage An error message as received from the business logic. When null,
   *                     it means that "all went well"
   * @return HTTP Response with either OK or BAD REQUEST status.
   */
  static ResponseEntity<String> createResponse(String errorMessage) {
    ResponseEntity<String> response;
    if (errorMessage == null) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    return response;
  }
}
