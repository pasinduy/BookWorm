package org.example.bookworm.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "book_details")
public class BookDetails {
    @Id
    private String bookDetailId;

    @ManyToOne
    private Book book;
    @ManyToOne
    private Borrow borrow;

    private String status;
    private LocalDate borrowDate;
    private LocalDate returnDate;

}