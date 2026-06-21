package lk.ijse.AADLibrarySystem.Controller;

import lk.ijse.AADLibrarySystem.Constant.CommonResponse;
import lk.ijse.AADLibrarySystem.DTO.BorrowRequestDTO;
import lk.ijse.AADLibrarySystem.Service.Impl.RecordServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/api/records")
public class RecordController {

    private final RecordServiceImpl recordService;

    public RecordController(RecordServiceImpl recordService) {
        this.recordService = recordService;
    }

    @PostMapping(value = "/borrow", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse borrowBook(@RequestBody BorrowRequestDTO borrowRequestDTO){
        return recordService.borrowBook(borrowRequestDTO);
    }
}
