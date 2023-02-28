package no.ntnu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the main HTML page - skeleton with some hidden elements.
 */
@Controller
public class PageSkeletonController {

  /**
   * The Main page - the skeleton. Some data will be later updated with Javascript.
   *
   * @return Name of the ThymeLeaf template to render
   */
  @GetMapping("/")
  public String getHome() {
    return "skeleton";
  }

}
