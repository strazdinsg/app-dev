package ntnu.ad.library.libraryjpadb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    public List<Book> findByName(String name);

    @Query("select bk.name from Book bk")
    public Book findBookName();

//    @Query("select max(bk.id) from Book bk")
//    public Integer findMaxId();
}
