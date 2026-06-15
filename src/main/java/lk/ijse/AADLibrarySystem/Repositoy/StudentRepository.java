package lk.ijse.AADLibrarySystem.Repositoy;

import lk.ijse.AADLibrarySystem.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
