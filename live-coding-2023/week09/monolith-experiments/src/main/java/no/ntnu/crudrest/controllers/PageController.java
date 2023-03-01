package no.ntnu.crudrest.controllers;

import no.ntnu.crudrest.repositories.AuthorRepository;
import no.ntnu.crudrest.repositories.BookRepository;
import no.ntnu.crudrest.services.BookService;
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
  BookService bookService;
  @Autowired
  AuthorRepository authorRepository;

  /**
   * Serve the "Home" page
   *
   * @return Name of the ThymeLeaf template which will be used to render the HTML
   */
  @GetMapping("/")
  public String getHome(Model model) {
    model.addAttribute("books", bookService.getFirst(2));
    return "index";
  }

  /**
   * Serve the "Books" page
   *
   * @return Name of the ThymeLeaf template which will be used to render the HTML
   */
  @GetMapping("/books")
  public String getBooks(Model model) {
    model.addAttribute("books", bookService.getAll());
    return "books";
  }

  /**
   * Serve the "About us" page
   *
   * @return Name of the ThymeLeaf template which will be used to render the HTML
   */
  @GetMapping("/about")
  public String getAboutUs(Model model) {
    model.addAttribute("bookCount", bookService.getCount());
    model.addAttribute("authorCount", authorRepository.count());
    return "about";
  }
}
