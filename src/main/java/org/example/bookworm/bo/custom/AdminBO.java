package org.example.bookworm.bo.custom;

import org.example.bookworm.bo.SuperBO;
import org.example.bookworm.dto.adminDto;
import org.example.bookworm.entity.Admin;

import java.sql.SQLException;

public interface AdminBO extends SuperBO {
    void updateBook(adminDto dto);

    adminDto viewCredentials(String text) throws SQLException, ClassNotFoundException;

    adminDto getUserDetails(String userId) throws SQLException, ClassNotFoundException;

    void saveUserDetails(adminDto dto);
}
