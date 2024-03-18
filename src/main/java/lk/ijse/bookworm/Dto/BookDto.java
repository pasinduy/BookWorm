package lk.ijse.bookworm.Dto;

import jakarta.persistence.ManyToOne;
import lk.ijse.bookworm.Entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {
    private String id;
    private String title;
    private String author;
    private String Genre;
    private String status;
    private Branch branch;
}
