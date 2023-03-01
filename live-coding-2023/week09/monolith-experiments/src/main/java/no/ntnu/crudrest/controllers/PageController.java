package no.ntnu.crudrest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Serves the HTML pages, with help of ThymeLeaf templates.
 */
@Controller
public class PageController {
  /**
   * Serve the "Home" page
   * @return Name of the ThymeLeaf template which will be used to render the HTML
   */
  @GetMapping("/")
  public String getHome() {
    return "index";
  }
}
