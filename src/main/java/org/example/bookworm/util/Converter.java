package org.example.bookworm.util;

import org.example.bookworm.dto.BookDto;
import org.example.bookworm.entity.Book;

public class Converter {
    private static Converter converter;

    private Converter(){}

    public static Converter getInstance(){return converter == null? converter = new Converter() : converter;}

    public Book toBookEntity(BookDto dto){
        Book book = new Book();
        book.setTitle(book.getTitle());
        book.setAuthor(book.getAuthor());
        book.setGenre(book.getGenre());
        book.setStatus(book.getStatus());
        return book;
    }

    public BookDto toBookDto(Book book){
        BookDto dto = new BookDto();
        dto.setTitle(dto.getTitle());
        dto.setAuthor(dto.getAuthor());
        dto.setGenre(dto.getGenre());
        dto.setStatus(dto.getStatus());
        return dto;
    }

}
