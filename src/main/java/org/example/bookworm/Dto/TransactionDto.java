package org.example.bookworm.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private String userName;
    private String book_id;
    private String book_Title;
    private Date borrowDate;
    private Date returnDate;
    private String status;
}
