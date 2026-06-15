package lk.ijse.AADLibrarySystem.Entity;

import jakarta.persistence.*;
import lk.ijse.AADLibrarySystem.Enumaration.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;
    private String studentName;
    private LocalDate dob;
    @Enumerated
    private StudentStatus status;

    @ManyToOne
    private Section section;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Record> recordsList;
}
