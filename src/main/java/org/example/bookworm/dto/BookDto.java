package org.example.bookworm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private String status;
}
