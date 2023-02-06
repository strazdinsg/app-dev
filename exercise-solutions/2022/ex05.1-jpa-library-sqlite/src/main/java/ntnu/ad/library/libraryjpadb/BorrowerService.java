package ntnu.ad.library.libraryjpadb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BorrowerService {
    @Autowired
    private BorrowerRepository borrowerRepository;

    @Transactional
    public String createBorrower(Borrower borrower){
        try{
            if(!borrowerRepository.existsByAddress(borrower.getAddress())){
                borrowerRepository.save(borrower);
                return "Borrower inserted!";
            }else{
                return "Borrower already exists in the DB";
            }
        }catch (Exception e){
            throw e;
        }

    }

    public List<Borrower> readBorrowers(){
        return borrowerRepository.findAll();
    }

    @Transactional
    public String updateBorrower(Borrower borrower){
        if(borrowerRepository.existsByAddress(borrower.getAddress())){
            try{
                List<Borrower> borrowers=borrowerRepository.findByAddress(borrower.getAddress());
                borrowers.stream().forEach(b->{
                    Borrower borrowerToBeUpdate = borrowerRepository.findById(b.getId()).get();
                    borrowerToBeUpdate.setName(borrower.getName());
                    borrowerToBeUpdate.setAddress(borrower.getAddress());
                    borrowerRepository.save(borrowerToBeUpdate);
                });
                return "Borrower info updated!";
            }catch(Exception e){
                throw e;
            }
        }else{
            return "Borrower does not exist in DB";
        }
    }

    @Transactional
    public String deleteBorrower(Borrower borrower){
        if(borrowerRepository.existsByAddress(borrower.getAddress())){
            try{
                List<Borrower> borrowers=borrowerRepository.findByAddress(borrower.getAddress());
                borrowers.stream().forEach(b->{
                    //System.out.println(b.toString());
                    borrowerRepository.delete(b);
                });
                return "Borrower is deleted successfully!";
            }catch (Exception e){
                throw e;
            }

        }else {
            return "Borrower does not exist in DB";
        }
    }

}
