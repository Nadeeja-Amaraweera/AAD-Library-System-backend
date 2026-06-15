package lk.ijse.AADLibrarySystem.DTO;

import jakarta.persistence.Enumerated;
import lk.ijse.AADLibrarySystem.Enumaration.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private long studentId;
    private String studentName;
    private LocalDate dob;
    private StudentStatus status;

    public StudentDTO(String studentName, LocalDate dob) {
        this.studentName = studentName;
        this.dob = dob;
    }
}
