package org.example.bookworm.bo.custom.Impl;

import org.example.bookworm.bo.custom.BookBo;
import org.example.bookworm.dao.custom.BookDaoImpl;
import org.example.bookworm.dto.BookDto;
import org.example.bookworm.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookBoImpl implements BookBo {
    BookDaoImpl bookDao = new BookDaoImpl();
    @Override
    public String generateNextBookId() throws Exception {
        return bookDao.generateNextId();
    }
    @Override
    public List<BookDto> getAllBooks() throws Exception {
        List<Book> books = bookDao.getAll();

        List<BookDto> bookDto = new ArrayList<>();

        for(Book book:books){
            bookDto.add(new BookDto(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getStatus(),
                    book.getBranch()
            ));
        }
        return bookDto;
    }

    @Override
    public boolean saveBook(BookDto bookDto) throws Exception {
        return bookDao.Save(new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getGenre(),
                bookDto.getStatus(),
                bookDto.getBranch()
        ));
    }
    @Override
    public boolean deleteBook(BookDto bookDto) throws Exception {
        return bookDao.delete(new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getGenre(),
                bookDto.getStatus(),
                bookDto.getBranch()
        ));
    }
    @Override
    public boolean updateBook(BookDto bookDto) throws Exception {
        return bookDao.update(new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getGenre(),
                bookDto.getStatus(),
                bookDto.getBranch()
        ));
    }
    @Override
    public BookDto searchBook(String id) throws Exception {
        Book book = bookDao.search(id);

        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getStatus(),
                book.getBranch()
        );
    }
}
