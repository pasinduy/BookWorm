package lk.ijse.bookworm.Bo.Custom;

import lk.ijse.bookworm.Bo.SuperBo;
import lk.ijse.bookworm.Dto.BookDto;

import java.util.List;

public interface BookBo extends SuperBo {
    String generateNextBookId() throws Exception ;

    List<BookDto> getAllBooks() throws Exception ;

    boolean saveBook(BookDto bookDto) throws Exception ;

    boolean deleteBook(BookDto bookDto) throws Exception ;

    boolean updateBook(BookDto bookDto) throws Exception ;

    BookDto searchBook(String id) throws Exception;
}
