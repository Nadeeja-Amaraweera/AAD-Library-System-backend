package lk.ijse.AADLibrarySystem.DTO;

import lk.ijse.AADLibrarySystem.Enumaration.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDTO {
    private long recordId;
    private long studentId;
    private String studentName;
    private long bookId;
    private String bookName;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private BookStatus bookStatus;
}
