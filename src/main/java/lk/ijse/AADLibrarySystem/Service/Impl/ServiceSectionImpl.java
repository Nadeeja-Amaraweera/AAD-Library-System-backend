package lk.ijse.AADLibrarySystem.Service.Impl;

import lk.ijse.AADLibrarySystem.DTO.SectionDTO;
import lk.ijse.AADLibrarySystem.Entity.Section;
import lk.ijse.AADLibrarySystem.Repositoy.SectionRepository;
import lk.ijse.AADLibrarySystem.Service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ServiceSectionImpl implements SectionService {

    private final SectionRepository sectionRepository;

    public ServiceSectionImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public SectionDTO saveSection(SectionDTO sectionDTO) {
        log.info("ServiceSectionImpl - saveSection() called ");
        try {
            Section section = new Section();
            section.setDescription(sectionDTO.getDescription());

            Section saveSection = sectionRepository.save(section);
            SectionDTO responseDTO = new SectionDTO();

            responseDTO.setSectionId(saveSection.getSectionId());
            responseDTO.setDescription(saveSection.getDescription());

            return responseDTO;

        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    @Override
    public SectionDTO updateSection(SectionDTO sectionDTO) {
        log.info("ServiceSectionImpl - updateSection() called ");
        try {

            Optional<Section> optionalSection = sectionRepository.findById(sectionDTO.getSectionId());
            if (!optionalSection.isPresent()){
                throw new RuntimeException("Section not found with ID: " + sectionDTO.getSectionId());
            }
            Section section = optionalSection.get();
            section.setSectionId(sectionDTO.getSectionId());
            section.setDescription(sectionDTO.getDescription());

            Section saveSection = sectionRepository.save(section);
            SectionDTO responseDTO = new SectionDTO();

            responseDTO.setSectionId(saveSection.getSectionId());
            responseDTO.setDescription(saveSection.getDescription());

            return responseDTO;

        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    @Override
    public SectionDTO getSectionById(long id) {
        log.info("ServiceSectionImpl - getSectionById() called ");
        try {

        Optional<Section> optionalSection = sectionRepository.findById(id);
        if (!optionalSection.isPresent()){
            throw new RuntimeException("Section not found with ID: " + id);
        }

            Section section = optionalSection.get();
            SectionDTO responseDTO = new SectionDTO();

            responseDTO.setSectionId(section.getSectionId());
            responseDTO.setDescription(section.getDescription());

            return responseDTO;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SectionDTO> getAllSections() {
        log.info("ServiceSectionImpl - getAllSections() called ");
        try {
            List<SectionDTO> sectionDTOList = new ArrayList<>();
            List<Section> sectionList = sectionRepository.findAll();


            for (Section section : sectionList){
                SectionDTO sectionDTO = new SectionDTO();
                sectionDTO.setSectionId(section.getSectionId());
                sectionDTO.setDescription(section.getDescription());
                sectionDTOList.add(sectionDTO);
            }

            return sectionDTOList;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
