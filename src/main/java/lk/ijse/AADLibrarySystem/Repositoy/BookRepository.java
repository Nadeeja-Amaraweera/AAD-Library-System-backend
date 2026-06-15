package lk.ijse.AADLibrarySystem.Repositoy;

import lk.ijse.AADLibrarySystem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
