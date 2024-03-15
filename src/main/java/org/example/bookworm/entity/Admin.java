package org.example.bookworm.entity;

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
