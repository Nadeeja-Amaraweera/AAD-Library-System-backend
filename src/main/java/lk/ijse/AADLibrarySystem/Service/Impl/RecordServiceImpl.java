package lk.ijse.AADLibrarySystem.Service.Impl;

import lk.ijse.AADLibrarySystem.Constant.CommonResponse;
import lk.ijse.AADLibrarySystem.Constant.ResponseMessage;
import lk.ijse.AADLibrarySystem.Constant.ResponseStatusCode;
import lk.ijse.AADLibrarySystem.DTO.BorrowRequestDTO;
import lk.ijse.AADLibrarySystem.DTO.RecordDTO;
import lk.ijse.AADLibrarySystem.DTO.ReturnRequestDTO;
import lk.ijse.AADLibrarySystem.Entity.Book;
import lk.ijse.AADLibrarySystem.Entity.Record;
import lk.ijse.AADLibrarySystem.Entity.Student;
import lk.ijse.AADLibrarySystem.Enumaration.BookStatus;
import lk.ijse.AADLibrarySystem.Repositoy.BookRepository;
import lk.ijse.AADLibrarySystem.Repositoy.RecordRepository;
import lk.ijse.AADLibrarySystem.Repositoy.StudentRepository;
import lk.ijse.AADLibrarySystem.Service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;

    public RecordServiceImpl(RecordRepository recordRepository, StudentRepository studentRepository, BookRepository bookRepository) {
        this.recordRepository = recordRepository;
        this.studentRepository = studentRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public CommonResponse borrowBook(BorrowRequestDTO borrowRequest) {
        log.info("RecordServiceImpl - borrowBook() called with Student ID: {} and Book ID: {}", borrowRequest.getStudentId(), borrowRequest.getBookId());
        try {

            Optional<Student> optionalStudent = studentRepository.findById(borrowRequest.getStudentId());
            if (!optionalStudent.isPresent()) {
                throw new RuntimeException("Student not found with ID: " + borrowRequest.getStudentId());
            }
            Student student = optionalStudent.get();
            log.info("Student found: {}", optionalStudent.get().getStudentName());


            Optional<Book> optionalBook = bookRepository.findById(borrowRequest.getBookId());
            if (!optionalBook.isPresent()) {
                throw new RuntimeException("Book not found with ID: " + borrowRequest.getBookId());
            }
            Book book = optionalBook.get();
            log.info("Book found: {}", optionalBook.get().getBookName());


            int activeBorrowings = recordRepository.countActiveBorrowingsBook(borrowRequest.getBookId());
            if (activeBorrowings > 0){
                return new CommonResponse(ResponseStatusCode.OPERATION_FAILED,"Book is currently borrowed by another student. Please try again later.", null);
            }

            Record record =  new Record();
            record.setStudent(student);
            record.setBook(book);
            record.setBorrowDate(LocalDate.now());
            record.setDueDate(LocalDate.now().plusWeeks(2));
            record.setBookStatus(String.valueOf(BookStatus.BORROWED));

            Record saveRecord = recordRepository.save(record);

            RecordDTO recordDTO = convertToDTO(saveRecord);

            return new CommonResponse(ResponseStatusCode.OPERATION_SUCCESS, recordDTO, ResponseMessage.SUCCESS_MESSAGE);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CommonResponse returnBook(ReturnRequestDTO returnRequest) {
        log.info("RecordServiceImpl - returnBook() called with Student ID: {} and Book ID: {}", returnRequest.getRecordId());
        try {
            Optional<Record> optionalRecord = recordRepository.findById(returnRequest.getRecordId());
            if (!optionalRecord.isPresent()) {
                throw new RuntimeException("Record not found with ID: " + returnRequest.getRecordId());
            }
            Record record = optionalRecord.get();
            if (record.getReturnDate() != null) {
                return new CommonResponse(ResponseStatusCode.OPERATION_FAILED, "Book has already been returned.", null);
            }

            record.setReturnDate(LocalDate.now());
            record.setBookStatus(String.valueOf(BookStatus.RETURNED));
            Record updateRecord = recordRepository.save(record);

            RecordDTO recordDTO = convertToDTO(updateRecord);

            return new CommonResponse(ResponseStatusCode.OPERATION_SUCCESS, recordDTO, ResponseMessage.SUCCESS_MESSAGE);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private RecordDTO convertToDTO(Record record){
        RecordDTO recordDTO = new RecordDTO();
        recordDTO.setRecordId(record.getRecordId());
        recordDTO.setStudentId(record.getStudent().getStudentId());
        recordDTO.setStudentName(record.getStudent().getStudentName());
        recordDTO.setBookId(record.getBook().getBookId());
        recordDTO.setBookName(record.getBook().getBookName());
        recordDTO.setBorrowDate(record.getBorrowDate());
        recordDTO.setDueDate(record.getDueDate());
        recordDTO.setReturnDate(record.getReturnDate());

        if (record.getReturnDate() != null){
            recordDTO.setBookStatus(BookStatus.RETURNED);
        } else if (LocalDate.now().isAfter(record.getDueDate())) {
            recordDTO.setBookStatus(BookStatus.OVERDUE);
        } else {
            recordDTO.setBookStatus(BookStatus.BORROWED);
        }
        return recordDTO;
    }
}
