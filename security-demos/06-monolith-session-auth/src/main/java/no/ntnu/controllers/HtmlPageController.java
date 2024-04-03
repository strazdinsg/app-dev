package no.ntnu.controllers;

import java.io.IOException;
import no.ntnu.dto.SignupDto;
import no.ntnu.dto.UserProfileDto;
import no.ntnu.models.User;
import no.ntnu.services.AccessUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * A controller serving our HTML pages.
 */
@Controller
public class HtmlPageController {
  @Autowired
  private AccessUserService userService;

  /**
   * Home page.
   *
   * @return the index.html Thymeleaf template name
   */
  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("user", userService.getSessionUser());
    return "index";
  }

  /**
   * Show profile page for a user.
   *
   * @param model    Model for passing data to Thymeleaf
   * @param username Username of the user
   * @return Name of the template to render
   */
  @GetMapping("users/{username}")
  public String userPage(Model model, @PathVariable String username) {
    return handleProfilePageRequest(username, null, model);
  }

  /**
   * This method handles HTTP POST - user submits changes to his/her profile.
   *
   * @param model    Model for passing data to Thymeleaf
   * @param username Username of the user
   * @return name of the Thymeleaf template to render the result
   */
  @PostMapping("users/{username}")
  public String userPagePost(@ModelAttribute UserProfileDto profileData,
                             @PathVariable String username, Model model) {
    return handleProfilePageRequest(username, profileData, model);
  }

  /**
   * Handler GET or POST request to the /users/{username} page. When the POST data is present,
   * update user profile data. Also checks if we are accessing a page which is allowed for
   * this user.
   *
   * @param username Username of the user profile to load
   * @param postData HTTP POST data submitted in the request. When not null, user profile
   *                 will be updated.
   * @param model    The model to put successMessage or errorMessage in
   * @return Name of the template to render: user on success, no-access if the request
   *     is unauthorized.
   */
  private String handleProfilePageRequest(String username, UserProfileDto postData, Model model) {
    User authenticatedUser = userService.getSessionUser();
    if (authenticatedUser != null && authenticatedUser.getUsername().equals(username)) {
      model.addAttribute("user", authenticatedUser);
      if (postData != null) {
        if (userService.updateProfile(authenticatedUser, postData)) {
          model.addAttribute("successMessage", "Profile updated!");
        } else {
          model.addAttribute("errorMessage", "Could not update profile data!");
        }
      }
      return "user";
    } else {
      return "no-access";
    }
  }

  /**
   * Handle HTTP GET requests to the admin page.
   *
   * @param model Model for passing data to Thymeleaf
   * @return name of the Thymeleaf template to render the result
   */
  @GetMapping("admin")
  public String adminPage(Model model) {
    // We still need the user for the navigation, even when we don't use it for the main content
    model.addAttribute("user", userService.getSessionUser());
    return "admin";
  }

  /**
   * Handle HTTP GET requests to the product page.
   *
   * @param model Model for passing data to Thymeleaf
   * @return name of the Thymeleaf template to render the result
   */
  @GetMapping("products")
  public String productPage(Model model) {
    // We still need the user for the navigation, even when we don't use it for the main content
    model.addAttribute("user", userService.getSessionUser());
    return "products";
  }

  @GetMapping("/login")
  public String loginForm() {
    return "login";
  }

  /**
   * Sign-up form.
   *
   * @return Name of Thymeleaf template to use
   */
  @GetMapping("/signup")
  public String signupForm() {
    return "signup-form";
  }

  /**
   * This method processes data received from the sign-up form (HTTP POST).
   *
   * @return Name of the template for the result page
   */
  @PostMapping("/signup")
  public String signupProcess(@ModelAttribute SignupDto signupData, Model model) {
    model.addAttribute("signupData", signupData);
    String templateName;
    try {
      userService.tryCreateNewUser(signupData.getUsername(), signupData.getPassword());
      templateName = "signup-success";
    } catch (IOException e) {
      model.addAttribute("errorMessage", e.getMessage());
      templateName = "signup-form";
    }
    return templateName;
  }
}
