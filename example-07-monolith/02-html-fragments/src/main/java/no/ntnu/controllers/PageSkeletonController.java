package no.ntnu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the main HTML page - empty skeleton.
 */
@Controller
public class PageSkeletonController {

  /**
   * The Main page - the skeleton. The content will be later loaded with Javascript.
   *
   * @return Name of the ThymeLeaf template to render
   */
  @GetMapping("/")
  public String getHome() {
    return "skeleton";
  }

}
