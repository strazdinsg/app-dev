package ntnu.ad.library.libraryjpadb;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Borrower {
    @Id
    private String id;

    private String name;
    private String address;

    public Borrower() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
