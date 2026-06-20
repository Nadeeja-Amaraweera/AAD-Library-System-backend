package lk.ijse.AADLibrarySystem.Controller;

import lk.ijse.AADLibrarySystem.Constant.CommonResponse;
import lk.ijse.AADLibrarySystem.DTO.BookDTO;
import lk.ijse.AADLibrarySystem.Service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static lk.ijse.AADLibrarySystem.Constant.ResponseMessage.SUCCESS_MESSAGE;
import static lk.ijse.AADLibrarySystem.Constant.ResponseStatusCode.OPERATION_SUCCESS;

@Slf4j
@RestController
@RequestMapping("v1/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveBook(@RequestBody BookDTO bookDTO){
        BookDTO bookDTO1 = bookService.saveBook(bookDTO);
        return new CommonResponse(OPERATION_SUCCESS, bookDTO1, SUCCESS_MESSAGE);
    }

    @PutMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateBook(@RequestBody BookDTO bookDTO) {
        BookDTO bookDTO1 = bookService.updateBook(bookDTO);
        return new CommonResponse(OPERATION_SUCCESS, bookDTO1, SUCCESS_MESSAGE);
    }
}
