package org.example.bookworm.bo.custom;

import org.example.bookworm.bo.SuperBO;
import org.example.bookworm.dto.AdminDto;

import java.sql.SQLException;

public interface AdminBO extends SuperBO {
    void updateAdmin(AdminDto dto);

    AdminDto viewCredentials(String text) throws SQLException, ClassNotFoundException;

    AdminDto getUserDetails(String userId) throws SQLException, ClassNotFoundException;

    void saveUserDetails(AdminDto dto);
}
