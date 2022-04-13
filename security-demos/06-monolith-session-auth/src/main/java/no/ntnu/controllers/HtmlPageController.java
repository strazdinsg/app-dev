package no.ntnu.controllers;

import no.ntnu.services.AccessUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * A controller serving our HTML pages
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
    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("user", userService.getSessionUser());
        return "index";
    }

    @GetMapping("users/{username}")
    public String userPage(Model model, @PathVariable String username) {
        UserDetails authenticatedUser = userService.getSessionUser();
        if (authenticatedUser != null && authenticatedUser.getUsername().equals(username)) {
            model.addAttribute("user", authenticatedUser);
            return "user";
        } else {
            return "no-access";
        }
    }

    @GetMapping("admin")
    public String adminPage(Model model) {
        // We still need the user for the navigation, even when we don't use it for the main content
        model.addAttribute("user", userService.getSessionUser());
        return "admin";
    }

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
}