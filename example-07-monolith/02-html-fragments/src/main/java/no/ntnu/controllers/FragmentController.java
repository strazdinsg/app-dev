package no.ntnu.controllers;

import no.ntnu.service.AuthorService;
import no.ntnu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for HTML fragments - the different parts of the page which will be requested
 * with Ajax requests from Javascript.
 */
@RequestMapping("/fragments")
@Controller
public class FragmentController {
  @Autowired
  BookService bookService;
  @Autowired
  AuthorService authorService;

  /**
   * Return an HTML fragment representing the content for the "Home" page.
   *
   * @param model The model where data will be stored (passed to ThymeLeaf template)
   * @return name of the ThymeLeaf template with the HTML content.
   */
  @GetMapping("/home")
  public String getHome(Model model) {
    model.addAttribute("books", bookService.getFirst(2));
    return "pages/home";
  }

  /**
   * Return an HTML fragment representing the content for the "Books" page.
   *
   * @param model The model where data will be stored (passed to ThymeLeaf template)
   * @return name of the ThymeLeaf template with the HTML content.
   */
  @GetMapping("/books")
  public String getBooks(Model model) {
    model.addAttribute("books", bookService.getAll());
    return "pages/books";
  }

  /**
   * Return an HTML fragment representing the content for the "About us" page.
   *
   * @param model The model where data will be stored (passed to ThymeLeaf template)
   * @return name of the ThymeLeaf template with the HTML content.
   */
  @GetMapping("/about")
  public String getAbout(Model model) {
    model.addAttribute("bookCount", bookService.getCount());
    model.addAttribute("authorCount", authorService.getCount());
    return "pages/about";
  }
}
