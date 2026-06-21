package lk.ijse.AADLibrarySystem.Service;

import lk.ijse.AADLibrarySystem.Constant.CommonResponse;
import lk.ijse.AADLibrarySystem.DTO.BorrowRequestDTO;
import lk.ijse.AADLibrarySystem.DTO.ReturnRequestDTO;

public interface RecordService {

    CommonResponse borrowBook(BorrowRequestDTO borrowRequestDTO);

     CommonResponse returnBook(ReturnRequestDTO returnRequestDTO);
}
