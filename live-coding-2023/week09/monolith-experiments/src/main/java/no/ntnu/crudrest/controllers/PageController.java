package no.ntnu.crudrest.controllers;

import no.ntnu.crudrest.repositories.AuthorRepository;
import no.ntnu.crudrest.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Serves the HTML pages, with help of ThymeLeaf templates.
 */
@Controller
public class PageController {
  @Autowired
  BookRepository bookRepository;
  @Autowired
  AuthorRepository authorRepository;

  /**
   * Serve the "Home" page
   *
   * @return Name of the ThymeLeaf template which will be used to render the HTML
   */
  @GetMapping("/")
  public String getHome() {
    return "index";
  }

  /**
   * Serve the "About us" page
   *
   * @return Name of the ThymeLeaf template which will be used to render the HTML
   */
  @GetMapping("/about")
  public String getAboutUs(Model model) {
    model.addAttribute("bookCount", bookRepository.count());
    model.addAttribute("authorCount", authorRepository.count());
    return "about";
  }
}
