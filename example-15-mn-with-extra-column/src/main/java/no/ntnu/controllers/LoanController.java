package no.ntnu.controllers;

import no.ntnu.model.Loan;
import no.ntnu.repositories.LoanRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API controller for loan data.
 */
@RestController
@RequestMapping("/loans")
public class LoanController {
  private final LoanRepository loanRepository;

  public LoanController(LoanRepository loanRepository) {
    this.loanRepository = loanRepository;
  }

  /**
   * Get all loans.
   *
   * @return All loans.
   */
  @GetMapping
  public Iterable<Loan> getAll() {
    return loanRepository.findAll();
  }
}
