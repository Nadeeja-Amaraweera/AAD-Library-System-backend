package lk.ijse.AADLibrarySystem.Service;

import lk.ijse.AADLibrarySystem.DTO.BookDTO;

import java.util.List;

public interface BookService {

    BookDTO saveBook(BookDTO bookDTO);

    BookDTO getBookById(BookDTO bookDTO);

    List<BookDTO> getAllBooks();

    BookDTO updateBook(BookDTO bookDTO);

}
