package lk.ijse.AADLibrarySystem.Repositoy;

import lk.ijse.AADLibrarySystem.Entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecordRepository extends JpaRepository<Record,Long> {

    @Query("SELECT COUNT(r) FROM Record r WHERE r.book.bookId = :bookId AND r.returnDate IS NULL")
    int countActiveBorrowingsBook(@Param("bookId") long bookId);
}
