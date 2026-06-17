package lk.ijse.AADLibrarySystem.Controller;

import lk.ijse.AADLibrarySystem.Constant.CommonResponse;
import lk.ijse.AADLibrarySystem.DTO.SectionDTO;
import lk.ijse.AADLibrarySystem.DTO.StudentDTO;
import lk.ijse.AADLibrarySystem.Service.SectionService;
import lk.ijse.AADLibrarySystem.Service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.AADLibrarySystem.Constant.ResponseMessage.SUCCESS_MESSAGE;
import static lk.ijse.AADLibrarySystem.Constant.ResponseStatusCode.OPERATION_SUCCESS;

@Slf4j
@RestController
@RequestMapping("/v1/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveStudent(@RequestBody StudentDTO studentDTO){
        log.info("SectionController - saveStudent() called");
        StudentDTO studentDTO1 = studentService.saveStudent(studentDTO);
        return new CommonResponse(OPERATION_SUCCESS, studentDTO1, SUCCESS_MESSAGE);
    }

    @PostMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateStudent(@RequestBody StudentDTO studentDTO){
        log.info("SectionController - updateStudent() called");
        StudentDTO studentDTO1 = studentService.updateStudent(studentDTO);
        return new CommonResponse(OPERATION_SUCCESS, studentDTO1, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/getAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllStudents(){
        log.info("SectionController - getAllStudents() called");
        List<StudentDTO> studentDTO1 = studentService.getAllStudents();
        return new CommonResponse(OPERATION_SUCCESS, studentDTO1, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/getById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getStudentById(@PathVariable long id) {
        log.info("SectionController - getStudentById() called with ID: {}", id);
        StudentDTO studentDTO = studentService.getStudentById(id);
        return new CommonResponse(OPERATION_SUCCESS, studentDTO, SUCCESS_MESSAGE);
    }

    @DeleteMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteStudent(@PathVariable long id) {
        log.info("SectionController - deleteStudent() called with ID: {}", id);
        StudentDTO studentDTO = studentService.deleteStudent(id);
        return new CommonResponse(OPERATION_SUCCESS, studentDTO, SUCCESS_MESSAGE);
    }

    @PatchMapping(value = "/addToSection/{sectionId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse addStudentToSection(@RequestBody StudentDTO studentDTO, @PathVariable long sectionId) {
        log.info("SectionController - addStudentToSection() called with studentId: {} and sectionId: {}", studentDTO, sectionId);
        StudentDTO studentDTO1 = studentService.addStudentToSections(studentDTO, sectionId);
        return new CommonResponse(OPERATION_SUCCESS, studentDTO1, SUCCESS_MESSAGE);
    }

}
