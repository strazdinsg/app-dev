package ntnu.ad.library.libraryjpadb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, String> {

    public boolean existsByAddress(String address);
    public List<Borrower> findByAddress(String address);

//    @Query("select max(b.id) from Borrower b")
//    public Integer findMaxId();
}
