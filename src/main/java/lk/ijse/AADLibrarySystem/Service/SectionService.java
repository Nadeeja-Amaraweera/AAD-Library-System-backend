package lk.ijse.AADLibrarySystem.Service;

import lk.ijse.AADLibrarySystem.DTO.SectionDTO;

import java.util.List;

public interface SectionService {

    SectionDTO saveSection(SectionDTO sectionDTO);

    SectionDTO updateSection(SectionDTO sectionDTO);

    SectionDTO getSectionById(long id);

    List<SectionDTO> getAllSections();
}
