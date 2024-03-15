package org.example.bookworm.bo.custom;

import org.example.bookworm.bo.SuperBo;
import org.example.bookworm.dto.BookDto;

import java.util.List;

public interface BookBo extends SuperBo {
    String generateNextBookId() throws Exception ;

    List<BookDto> getAllBooks() throws Exception ;

    boolean saveBook(BookDto bookDto) throws Exception ;

    boolean deleteBook(BookDto bookDto) throws Exception ;

    boolean updateBook(BookDto bookDto) throws Exception ;

    BookDto searchBook(String id) throws Exception;
}
