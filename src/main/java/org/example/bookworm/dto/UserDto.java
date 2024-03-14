package org.example.bookworm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userID;
    private String email;
    private String name;
    private String address;
    private String contact;
    private Date dob;
    private String password;
}
