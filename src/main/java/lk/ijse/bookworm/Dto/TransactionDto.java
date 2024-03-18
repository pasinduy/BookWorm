package lk.ijse.bookworm.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class TransactionDto {
    private String userName;
    private String book_id;
    private String book_Title;
    private Date borrowDate;
    private Date returnDate;
    private String status;
}
