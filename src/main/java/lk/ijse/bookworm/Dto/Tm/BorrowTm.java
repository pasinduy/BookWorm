package lk.ijse.bookworm.Dto.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class BorrowTm {
    private String memberId;
    private String bookId;
    private String status;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
