package no.ntnu.controllers;

import no.ntnu.model.Borrower;
import no.ntnu.repositories.BorrowerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API controller for borrowers.
 */
@RestController
@RequestMapping("/borrowers")
public class BorrowerController {
  private final BorrowerRepository borrowerRepository;

  public BorrowerController(BorrowerRepository borrowerRepository) {
    this.borrowerRepository = borrowerRepository;
  }

  /**
   * Get all the borrowers.
   *
   * @return List of all the borrowers
   */
  @GetMapping
  public Iterable<Borrower> getAll() {
    return borrowerRepository.findAll();
  }
}
