package lk.ijse.bookworm.Bo.Custom;

import lk.ijse.bookworm.Bo.SuperBo;
import lk.ijse.bookworm.Dto.UserDto;
import lk.ijse.bookworm.Entity.User;

import java.util.ArrayList;
import java.util.List;

public interface UserBo extends SuperBo {
    List<UserDto> getAllUser() throws Exception ;

    String generateNextUserId() throws Exception;

    boolean saveUser(UserDto userDto) throws Exception;

    boolean DeleteUser(UserDto userDto) throws Exception ;

    boolean updateUser(UserDto userDto) throws Exception ;

    UserDto SearchUser(String id) throws Exception;
}
