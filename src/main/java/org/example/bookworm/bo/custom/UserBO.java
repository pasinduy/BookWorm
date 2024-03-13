package org.example.bookworm.bo.custom;

import org.example.bookworm.bo.SuperBO;
import org.example.bookworm.dto.UserDto;

import java.util.List;

public interface UserBO extends SuperBO {
    void saveUser(UserDto dto);

    void updateUser(UserDto dto);

    List<UserDto> getAllTenants();

    String getLastId();

    void deleteUser(String ID);

    UserDto viewUser(UserDto dto);

    String getTenantsCount();
}
