package org.example.bookworm.bo.custom;

import org.example.bookworm.bo.SuperBO;
import org.example.bookworm.dto.BookDto;
import org.example.bookworm.dto.BorrowDto;

import java.util.List;

public interface BorrowBO extends SuperBO {
    void saveTenant(BorrowDto dto);

    void updateTenant(BorrowDto dto);

    List<BorrowDto> getAllBooks();

    String getLastId();

    void deleteTenant(String borrowId);

    BorrowDto viewTenant(BorrowDto dto);
}
