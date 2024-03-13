package org.example.bookworm.bo.custom;

import org.example.bookworm.bo.SuperBO;
import org.example.bookworm.dto.BookDto;

import java.util.List;

public interface BookBO extends SuperBO {
    void saveBook(BookDto dto);

    void updateBook(BookDto dto);

    List<BookDto> getAllBooks();

    String getLastId();

    void deleteBook(String bookId);

    BookDto viewBook(BookDto dto);
}
