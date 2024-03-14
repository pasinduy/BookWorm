package org.example.bookworm.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTm {
    private String username;
    private String email;
    private String name;
    private String address;
    private String contact;
    private Date dob;
    private String NoOfBooks;
}
