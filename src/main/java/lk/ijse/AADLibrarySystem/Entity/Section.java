package lk.ijse.AADLibrarySystem.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long sectionId;
    private String description;

    @OneToMany(mappedBy = "section",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Student> studentsList;


}
