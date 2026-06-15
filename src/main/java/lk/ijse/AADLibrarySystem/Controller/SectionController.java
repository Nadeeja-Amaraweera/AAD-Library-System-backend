package lk.ijse.AADLibrarySystem.Controller;

import lk.ijse.AADLibrarySystem.Constant.CommonResponse;
import lk.ijse.AADLibrarySystem.DTO.SectionDTO;
import lk.ijse.AADLibrarySystem.Service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.AADLibrarySystem.Constant.ResponseMessage.SUCCESS_MESSAGE;
import static lk.ijse.AADLibrarySystem.Constant.ResponseStatusCode.OPERATION_SUCCESS;

@Slf4j
@RestController
@RequestMapping("/v1/api/sections")
public class SectionController {

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    private final SectionService sectionService;

    @PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveSection(@RequestBody SectionDTO sectionDTO){
        log.info("SectionController - saveSection() called");
        SectionDTO sectionDTO1 = sectionService.saveSection(sectionDTO);
        return new CommonResponse(OPERATION_SUCCESS, sectionDTO1, SUCCESS_MESSAGE);
    }

    @PostMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateSection(@RequestBody SectionDTO sectionDTO){
        log.info("SectionController - updateSection() called");
        SectionDTO sectionDTO1 = sectionService.updateSection(sectionDTO);
        return new CommonResponse(OPERATION_SUCCESS, sectionDTO1, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/getById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getSectionById(@PathVariable long id) {
        log.info("SectionController - getSectionById() called with ID: {}", id);
        SectionDTO sectionDTO = sectionService.getSectionById(id);
        return new CommonResponse(OPERATION_SUCCESS, sectionDTO, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/getAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllSections() {
        log.info("SectionController - getAllSections() called");
        List<SectionDTO> sectionDTOList = sectionService.getAllSections();
        return new CommonResponse(OPERATION_SUCCESS, sectionDTOList, SUCCESS_MESSAGE);
    }

}
