package lk.ijse.bookworm.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.lang.model.element.Name;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table (name = "Book")
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String Genre;
    private String status;

    @ManyToOne
    private Branch branch;

    @OneToMany(mappedBy = "book")
    private List<BookDetails> bookDetails;

    public Book(String id, String title, String author, String genre, String status, Branch branch) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.Genre = genre;
        this.status = status;
        this.branch = branch;
    }

    public Book(String id) {
        this.id = id;
    }
}
