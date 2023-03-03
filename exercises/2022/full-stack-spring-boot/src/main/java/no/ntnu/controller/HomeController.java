package no.ntnu.controller;

import no.ntnu.service.AuthorService;
import no.ntnu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * MVC controller for the main page(s). Returns HTML pages, not JSON!
 */
@Controller
public class HomeController {
  @Autowired
  private AuthorService authorService;
  @Autowired
  private BookService bookService;

  /**
   * Responds to HTTP get /
   *
   * @return Name of the template to render
   */
  @GetMapping("/")
  public String getHome(Model model) {
    model.addAttribute("authors", authorService.getAll());
    model.addAttribute("books", bookService.getAll());
    return "index";
  }
}
