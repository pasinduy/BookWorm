package org.example.bookworm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bookworm.entity.Branch;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String id;
    private String title;
    private String author;
    private String Genre;
    private String status;
    private Branch branch;
}
