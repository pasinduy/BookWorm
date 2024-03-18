package lk.ijse.bookworm.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Borrow")
public class Borrow {
    @Id
    private String borrowId;
    private LocalDate date;

    @OneToMany(mappedBy = "borrow")
    private List<BookDetails> bookDetails;

    @ManyToOne
    private User user;

    public Borrow(String borrowId, LocalDate date, User user) {
        this.borrowId = borrowId;
        this. date= date;
        this.user = user;
    }

    public Borrow(String borrowId) {
        this.borrowId = borrowId;
    }
}
