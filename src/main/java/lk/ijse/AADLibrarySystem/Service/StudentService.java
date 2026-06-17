package lk.ijse.AADLibrarySystem.Service;

import lk.ijse.AADLibrarySystem.DTO.SectionDTO;
import lk.ijse.AADLibrarySystem.DTO.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO saveStudent(StudentDTO studentDTO);

    StudentDTO updateStudent(StudentDTO studentDTO);

    StudentDTO getStudentById(long id);

    List<StudentDTO> getAllStudents();

    StudentDTO deleteStudent(long id);

    StudentDTO addStudentToSections(StudentDTO studentDTO, long sectionId);
}
