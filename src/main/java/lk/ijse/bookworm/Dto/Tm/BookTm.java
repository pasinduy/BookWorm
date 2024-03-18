package lk.ijse.bookworm.Dto.Tm;

import lk.ijse.bookworm.Entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookTm {
    private String id;
    private String title;
    private String author;
    private String Genre;
    private String status;
    private String branch;
}
