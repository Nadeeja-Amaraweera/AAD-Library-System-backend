package lk.ijse.AADLibrarySystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowRequestDTO {
    private long studentId;
    private long bookId;
}
