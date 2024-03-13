package org.example.bookworm.bo.custom;

import org.example.bookworm.bo.SuperBO;
import org.example.bookworm.dto.BookDto;

import java.util.List;

public interface BookBO extends SuperBO {
    void saveTenant(BookDto dto);

    void updateTenant(BookDto dto);

    List<BookDto> getAllBooks();

    String getLastId();

    void deleteTenant(String bookId);

    BookDto viewTenant(BookDto dto);
}
