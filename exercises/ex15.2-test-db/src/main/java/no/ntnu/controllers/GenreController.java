package no.ntnu.controllers;

import no.ntnu.model.Genre;
import no.ntnu.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API controller for genre endpoints.
 */
@RestController
@RequestMapping("genres")
public class GenreController {
  @Autowired
  private GenreService genreService;

  /**
   * Get All genres. (HTTP GET /genres endpoint)
   *
   * @return HTTP response with all the genres
   */
  @GetMapping
  public Iterable<Genre> getAll() {
    return genreService.getAll();
  }
}
