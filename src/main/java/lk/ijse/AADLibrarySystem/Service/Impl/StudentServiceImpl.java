package lk.ijse.AADLibrarySystem.Service.Impl;

import lk.ijse.AADLibrarySystem.DTO.StudentDTO;
import lk.ijse.AADLibrarySystem.Entity.Student;
import lk.ijse.AADLibrarySystem.Enumaration.StudentStatus;
import lk.ijse.AADLibrarySystem.Repositoy.StudentRepository;
import lk.ijse.AADLibrarySystem.Service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        log.info("StudentServiceImpl - saveStudent() called");
        try {

            Student student =  new Student();
            student.setStudentName(studentDTO.getStudentName());
            student.setDob(studentDTO.getDob());
            student.setStatus(StudentStatus.ACTIVE);

            Student saveStudent = studentRepository.save(student);
            StudentDTO responseDTO = new StudentDTO();
            responseDTO.setStudentId(saveStudent.getStudentId());
            responseDTO.setStudentName(saveStudent.getStudentName());
            responseDTO.setDob(saveStudent.getDob());
            responseDTO.setStatus(saveStudent.getStatus());

            return responseDTO;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        log.info("StudentServiceImpl - updateStudent() called");
        try {

            Optional<Student> optionalStudent = studentRepository.findById(studentDTO.getStudentId());
            if (!optionalStudent.isPresent()){
                throw new RuntimeException("Student not found with ID: " + studentDTO.getStudentId());
            }

            Student student = optionalStudent.get();
            student.setStudentId(studentDTO.getStudentId());
            student.setStudentName(studentDTO.getStudentName());
            student.setDob(studentDTO.getDob());
            student.setStatus(studentDTO.getStatus());

            Student updateStudent = studentRepository.save(student);

            StudentDTO responseDTO = new StudentDTO();
            responseDTO.setStudentId(updateStudent.getStudentId());
            responseDTO.setStudentName(updateStudent.getStudentName());
            responseDTO.setDob(updateStudent.getDob());
            responseDTO.setStatus(updateStudent.getStatus());

            return responseDTO;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentDTO getStudentById(long id) {
        log.info("StudentServiceImpl - getSectionById() called");
        try {

            Optional<Student> optionalStudent = studentRepository.findById(id);
            if (!optionalStudent.isPresent()) {
                throw new RuntimeException("Student not found with ID: " + id);
            }
            Student student = optionalStudent.get();
            StudentDTO responseDTO = new StudentDTO();
            responseDTO.setStudentId(student.getStudentId());
            responseDTO.setStudentName(student.getStudentName());
            responseDTO.setDob(student.getDob());
            responseDTO.setStatus(student.getStatus());

            return responseDTO;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        log.info("StudentServiceImpl - getAllStudents() called");
        try {

            List<StudentDTO> studentDTOS = new ArrayList<>();

            List<Student> students = studentRepository.findAll();
            for (Student student: students){
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setStudentId(student.getStudentId());
                studentDTO.setStudentName(student.getStudentName());
                studentDTO.setDob(student.getDob());
                studentDTO.setStatus(student.getStatus());
                studentDTOS.add(studentDTO);
            }
            return studentDTOS;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteStudent(long id) {

    }
}
