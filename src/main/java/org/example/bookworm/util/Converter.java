package org.example.bookworm.util;

import org.example.bookworm.dto.*;
import org.example.bookworm.entity.*;

public class Converter {
    private static Converter converter;

    private Converter(){}

    public static Converter getInstance(){return converter == null? converter = new Converter() : converter;}

    public Admin toAdminEntity (AdminDto dto){
        Admin admin = new Admin();

        return admin;
    }
    public Book toBookEntity(BookDto dto){
        Book book = new Book();
        book.setTitle(book.getTitle());
        book.setAuthor(book.getAuthor());
        book.setGenre(book.getGenre());
        book.setStatus(book.getStatus());
        return book;
    }

    public Borrow toBorrowEntity(BorrowDto dto){
        Borrow borrow = new Borrow();

        return borrow;
    }

    public Branch toBranchEntity(BranchDto dto){
        Branch branch = new Branch();

        return branch;
    }

    public User toUserEntity(UserDto dto){
        User user = new User();
        user.setUserID(user.getUserID());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        return user;
    }

    public AdminDto toAdminDto(Admin admin){
        AdminDto dto = new AdminDto();

        return dto;
    }

    public BorrowDto toBorrowDto(Borrow borrow){
        BorrowDto dto = new BorrowDto();

        return dto;
    }

    public BookDto toBookDto(Book book){
        BookDto dto = new BookDto();
        dto.setTitle(dto.getTitle());
        dto.setAuthor(dto.getAuthor());
        dto.setGenre(dto.getGenre());
        dto.setStatus(dto.getStatus());
        return dto;
    }

    public BranchDto toBranchDto (Branch branch){
        BranchDto dto = new BranchDto();

        return dto;
    }

    public UserDto toUserDto (User user) {
        UserDto dto = new UserDto();
        dto.setUserID(dto.getUserID());
        dto.setEmail(dto.getEmail());
        dto.setPassword(dto.getPassword());
        return dto;
    }

}
