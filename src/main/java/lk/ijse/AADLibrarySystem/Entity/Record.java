package lk.ijse.AADLibrarySystem.Entity;

import jakarta.persistence.*;
import lk.ijse.AADLibrarySystem.Enumaration.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recordId;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Book book;

    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    private String bookStatus;

}
