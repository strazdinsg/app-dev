package no.ntnu.ex111;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Handles HTTP responses - serves HTML pages, with the help of ThymeLeaf.
 */
@Controller
public class PageController {
  /**
   * Home page.
   *
   * @return Name of the ThymeLeaf template to render
   */
  @GetMapping("/")
  public String getIndex() {
    return "index";
  }
  /**
   * Books page.
   *
   * @return Name of the ThymeLeaf template to render
   */
  @GetMapping("/books")
  public String getBooks() {
    return "books";
  }
  /**
   * About-us page.
   *
   * @return Name of the ThymeLeaf template to render
   */
  @GetMapping("/about")
  public String getAboutUs() {
    return "about";
  }
}
