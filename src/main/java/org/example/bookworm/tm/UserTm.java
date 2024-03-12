package org.example.bookworm.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTm {
    private String username;
    private String email;
    private String NoOfBooks;
}
