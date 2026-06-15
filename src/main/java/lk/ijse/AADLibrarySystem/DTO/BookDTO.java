package lk.ijse.AADLibrarySystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private long bookId;
    private String bookName;
    private String author;

    public BookDTO(String bookName, String author) {
        this.bookName = bookName;
        this.author = author;
    }
}
