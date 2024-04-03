package no.ntnu.controllers;

import no.ntnu.dto.UserProfileDto;
import no.ntnu.models.User;
import no.ntnu.services.AccessUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API controller serving endpoints for users.
 */
@CrossOrigin
@RestController
public class UserController {
  @Autowired
  private AccessUserService userService;

  /**
   * Return user profile information.
   *
   * @param username Username for which the profile is requested
   * @return The profile information or error code when not authorized
   */
  @GetMapping("/api/users/{username}")
  public ResponseEntity<?> getProfile(@PathVariable String username) {
    User sessionUser = userService.getSessionUser();
    if (sessionUser != null && sessionUser.getUsername().equals(username)) {
      UserProfileDto profile = new UserProfileDto(sessionUser.getBio());
      simulateLongOperation();
      return new ResponseEntity<>(profile, HttpStatus.OK);
    } else if (sessionUser == null) {
      return new ResponseEntity<>("Profile data accessible only to authenticated users",
          HttpStatus.UNAUTHORIZED);
    } else {
      return new ResponseEntity<>("Profile data for other users not accessible!",
          HttpStatus.FORBIDDEN);
    }
  }

  private static void simulateLongOperation() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Update user profile information.
   *
   * @param username Username for which the profile is updated
   * @return HTTP 200 OK or error code with error message
   */
  @PutMapping("/api/users/{username}")
  public ResponseEntity<String> updateProfile(@PathVariable String username,
                                              @RequestBody UserProfileDto profileData) {
    User sessionUser = userService.getSessionUser();
    ResponseEntity<String> response;
    if (sessionUser != null && sessionUser.getUsername().equals(username)) {
      if (profileData != null) {
        if (userService.updateProfile(sessionUser, profileData)) {
          simulateLongOperation();
          response = new ResponseEntity<>("", HttpStatus.OK);
        } else {
          response = new ResponseEntity<>("Could not update Profile data",
              HttpStatus.INTERNAL_SERVER_ERROR);
        }
      } else {
        response = new ResponseEntity<>("Profile data not supplied", HttpStatus.BAD_REQUEST);
      }
    } else if (sessionUser == null) {
      response = new ResponseEntity<>("Profile data accessible only to authenticated users",
          HttpStatus.UNAUTHORIZED);
    } else {
      response = new ResponseEntity<>("Profile data for other users not accessible!",
          HttpStatus.FORBIDDEN);
    }
    return response;
  }
}
