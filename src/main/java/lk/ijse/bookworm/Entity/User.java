package lk.ijse.bookworm.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "User")
public class User {
    @Id
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "user")
    private List<Borrow> borrow;

    public User(String id, String name, String address, String email, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
}
