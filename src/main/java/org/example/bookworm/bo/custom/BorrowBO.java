package org.example.bookworm.bo.custom;

import org.example.bookworm.bo.SuperBO;
import org.example.bookworm.dto.BookDto;
import org.example.bookworm.dto.BorrowDto;

import java.util.List;

public interface BorrowBO extends SuperBO {
    BorrowDto viewBorrow(String id);

    void saveBorrow(BorrowDto borrow);

    void updateBorrow(BorrowDto reservation);

    void deleteBorrow(String text);

    Integer getBorrowCount(String borrowId);

    String getLastId();

    List<BorrowDto> getAll();

}
