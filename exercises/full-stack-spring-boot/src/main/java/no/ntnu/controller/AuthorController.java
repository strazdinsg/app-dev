package no.ntnu.controller;

import no.ntnu.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * MVC controller for author-related pages. Returns HTML pages, not JSON!
 */
@Controller
@RequestMapping("authors")
public class AuthorController {
  @Autowired
  AuthorService authorService;

  /**
   * Get details for a specific author
   *
   * @param id ID of the author to be returned
   * @return template which will render the page
   */
  @GetMapping("/{id}")
  public String getOne(@PathVariable Integer id, Model model) {
    model.addAttribute("author", authorService.findById(id));
    return "author-details";
  }
}
