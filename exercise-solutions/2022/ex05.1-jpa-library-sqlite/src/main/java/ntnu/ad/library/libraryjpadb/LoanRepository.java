package ntnu.ad.library.libraryjpadb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {

//    public List<Loan> findByBId(Integer bid);

    @Query("select l.bookid from Loan l")
    public Loan findLoanedBook();

//    @Query("select max(l.id) from Loan l")
//    public Integer findMaxId();


}
