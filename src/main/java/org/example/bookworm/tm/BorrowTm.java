package org.example.bookworm.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowTm {
    private String memberId;
    private String bookId;
    private String status;
    private LocalDate borrowDate;
    private LocalDate returnDate;

}
