package lk.ijse.AADLibrarySystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDTO {
    private long sectionId;
    private String description;

    public SectionDTO(String description) {
        this.description = description;
    }
}
