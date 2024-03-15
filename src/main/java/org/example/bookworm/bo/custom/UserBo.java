package org.example.bookworm.bo.custom;

import org.example.bookworm.bo.SuperBo;
import org.example.bookworm.dto.UserDto;

import java.util.List;

public interface UserBo extends SuperBo {
    List<UserDto> getAllUser() throws Exception ;

    String generateNextUserId() throws Exception;

    boolean saveUser(UserDto userDto) throws Exception;

    boolean DeleteUser(UserDto userDto) throws Exception ;

    boolean updateUser(UserDto userDto) throws Exception ;

    UserDto SearchUser(String id) throws Exception;
}
