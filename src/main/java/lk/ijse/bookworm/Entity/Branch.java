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
@Table (name = "Branch")
public class Branch {

    @Id
    private String id;
    private String name;
    private String location;
    private String Address;

    @ManyToOne
    private Admin admin;

    @OneToMany(mappedBy = "branch")
    List<Book> books;

}
