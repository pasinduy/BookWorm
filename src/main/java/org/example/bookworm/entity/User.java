package org.example.bookworm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class User {
    @Id
    private String userID;
    private String email;
    private String name;
    private String address;
    private String contact;
    private Date dob;
    private String password;
}
