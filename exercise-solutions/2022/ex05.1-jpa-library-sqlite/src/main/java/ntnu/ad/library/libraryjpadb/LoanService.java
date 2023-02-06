package ntnu.ad.library.libraryjpadb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Transactional
    public String createLoan(Loan loan){
        try{
            if(!loanRepository.existsById(loan.getId())){

                loanRepository.save(loan);
                return "Loan inserted!";
            }else{
                return "Loan already exists in the DB";
            }
        }catch (Exception e){
            throw e;
        }

    }

    public List<Loan> readLoans(){
        return loanRepository.findAll();
    }

    @Transactional
    public String updateLoan(Loan loan){
        if(loanRepository.existsById(loan.getId())){
            try{
                Loan loanToBeUpdate = loanRepository.findById(loan.getId()).get();
                loanToBeUpdate.setBookid(loan.getBookid());
                loanToBeUpdate.setBorrowerid(loan.getBorrowerid());
                loanToBeUpdate.setBorrow_date(loan.getBorrow_date());
                loanToBeUpdate.setDue_date(loan.getDue_date());
                loanRepository.save(loanToBeUpdate);
                return "Loan info updated!";
            }catch(Exception e){
                throw e;
            }
        }else{
            return "Loan does not exist in DB";
        }
    }

    @Transactional
    public String deleteLoan(Loan loan){
        if(loanRepository.existsById(loan.getId())){
            try{
                loanRepository.delete(loan);
                return "Loan is deleted successfully!";
            }catch (Exception e){
                throw e;
            }

        }else {
            return "Loan does not exist in DB";
        }
    }
}
