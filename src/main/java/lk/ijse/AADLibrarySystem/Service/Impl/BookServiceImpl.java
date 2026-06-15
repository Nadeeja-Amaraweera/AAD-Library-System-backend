package lk.ijse.AADLibrarySystem.Service.Impl;

import lk.ijse.AADLibrarySystem.DTO.BookDTO;
import lk.ijse.AADLibrarySystem.Entity.Book;
import lk.ijse.AADLibrarySystem.Repositoy.BookRepository;
import lk.ijse.AADLibrarySystem.Service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO) {
        log.info("BookServiceImpl - saveBook() called");
        try {

        Book book = new Book();
        book.setBookName(bookDTO.getBookName());
        book.setAuthor(bookDTO.getAuthor());

        Book saveBook = bookRepository.save(book);

        BookDTO responseDTO = new BookDTO();
        responseDTO.setBookId(saveBook.getBookId());
        responseDTO.setBookName(saveBook.getBookName());
        responseDTO.setAuthor(saveBook.getAuthor());

        return responseDTO;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookDTO getBookById(BookDTO bookDTO) {
        log.info("BookServiceImpl - getBookById() called with ID: {}", bookDTO.getBookId());
        try {
            Optional<Book> optionalBook = bookRepository.findById(bookDTO.getBookId());

            Book book = optionalBook.get();
            book.setBookId(bookDTO.getBookId());
            book.setBookName(bookDTO.getBookName());
            book.setAuthor(bookDTO.getAuthor());

            BookDTO responseDTO = new BookDTO();
            responseDTO.setBookId(book.getBookId());
            responseDTO.setBookName(book.getBookName());
            responseDTO.setAuthor(book.getAuthor());

            return responseDTO;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BookDTO> getAllBooks() {
        log.info("BookServiceImpl - getAllBooks() called");
        try {
            List<BookDTO> bookDTOList = new ArrayList<>();
            List<Book> bookList = bookRepository.findAll();

            for (Book book : bookList){
                BookDTO bookDTO = new BookDTO();
                bookDTO.setBookId(book.getBookId());
                bookDTO.setBookName(book.getBookName());
                bookDTO.setAuthor(book.getAuthor());
                bookDTOList.add(bookDTO);
            }
            return bookDTOList;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO) {

        log.info("BookServiceImpl - updateBook() called with ID: {}", bookDTO.getBookId());
        try {

            Optional<Book> optionalBook = bookRepository.findById(bookDTO.getBookId());
            if (!optionalBook.isPresent()){
                throw new RuntimeException("Book not found with ID: " + bookDTO.getBookId());
            }

            Book book = optionalBook.get();
            book.setBookId(bookDTO.getBookId());
            book.setBookName(bookDTO.getBookName());
            book.setAuthor(bookDTO.getAuthor());

            Book updateBook = bookRepository.save(book);
            BookDTO responseDTO = new BookDTO();
            responseDTO.setBookId(updateBook.getBookId());
            responseDTO.setBookName(updateBook.getBookName());
            responseDTO.setAuthor(updateBook.getAuthor());

            return responseDTO;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }




}
