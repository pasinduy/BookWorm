package org.example.bookworm.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookTm {
    private String id;
    private String title;
    private String author;
    private String Genre;
    private String status;
    private String branch;
}
