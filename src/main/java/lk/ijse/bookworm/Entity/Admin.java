package lk.ijse.bookworm.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data


@Entity
@Table(name =" Admin")
public class Admin {
    @Id
    private String id;
    private String name;
    private String password;

    @OneToMany(mappedBy = "admin")
    private List<Branch> branches;

    public Admin(String name, String password, List<Branch> branches) {
        this.name = name;
        this.password = password;
        this.branches = branches;
    }
}
