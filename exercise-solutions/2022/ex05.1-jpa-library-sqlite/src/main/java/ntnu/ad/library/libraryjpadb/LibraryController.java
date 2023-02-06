package ntnu.ad.library.libraryjpadb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryController {

    @Autowired
    private BorrowerService borrowerService;
    @Autowired
    private BookService bookService;
    @Autowired
    private LoanService loanService;

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String info(){
        return "The Library application is on...";
    }

    ////Borrower requests.
    @RequestMapping(value = "borrower", method = RequestMethod.POST)
    public String createBorrower(@RequestBody Borrower borrower){
        return borrowerService.createBorrower(borrower);
    }

    @RequestMapping(value = "borrower", method = RequestMethod.GET)
    public List<Borrower> readBorrowers(){
        return borrowerService.readBorrowers();
    }

    @RequestMapping(value = "borrower/{id}", method = RequestMethod.PUT)
    public String updateBorrower(@RequestBody Borrower borrower){
        return borrowerService.updateBorrower(borrower);
    }

    @RequestMapping(value = "borrower", method = RequestMethod.DELETE)
    public String deleteBorrower(@RequestBody Borrower borrower){
        return borrowerService.deleteBorrower(borrower);
    }

    //////Book requests.

    @RequestMapping(value = "book", method = RequestMethod.POST)
    public String createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @RequestMapping(value = "book", method = RequestMethod.GET)
    public List<Book> readBooks(){
        return bookService.readBooks();
    }

    @RequestMapping(value = "book/{id}", method = RequestMethod.PUT)
    public String updateBook(@RequestBody Book book){
        return bookService.updateBook(book);
    }

    @RequestMapping(value = "book", method = RequestMethod.DELETE)
    public String deleteBook(@RequestBody Book book){
        return bookService.deleteBook(book);
    }

    //////Loan requests.

    @RequestMapping(value = "loan", method = RequestMethod.POST)
    public String createLoan(@RequestBody Loan loan){
        return loanService.createLoan(loan);
    }

    @RequestMapping(value = "loan", method = RequestMethod.GET)
    public List<Loan> readLoans(){
        return loanService.readLoans();
    }

    @RequestMapping(value = "loan/{id}", method = RequestMethod.PUT)
    public String updateLoan(@RequestBody Loan loan){
        return loanService.updateLoan(loan);
    }

    @RequestMapping(value = "loan", method = RequestMethod.DELETE)
    public String deleteBook(@RequestBody Loan loan){
        return loanService.deleteLoan(loan);
    }
}
