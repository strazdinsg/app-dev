package ntnu.ad.library.libraryjpadb;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Loan {
    @Id
    private String id;

//    @ManyToOne
    private String borrowerid;
//    @ManyToOne
    private String bookid;

    private String borrow_date;
    private String due_date;


    public Loan() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBorrowerid() {
        return borrowerid;
    }

    public void setBorrowerid(String borrowerid) {
        this.borrowerid = borrowerid;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(String borrow_date) {
        this.borrow_date = borrow_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
}
