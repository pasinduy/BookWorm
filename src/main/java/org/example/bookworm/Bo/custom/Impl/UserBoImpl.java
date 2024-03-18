package org.example.bookworm.Bo.custom.Impl;

import org.example.bookworm.Bo.custom.UserBo;
import org.example.bookworm.Dao.custom.Impl.UserDAOImpl;
import org.example.bookworm.Dto.UserDto;
import org.example.bookworm.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {

    UserDAOImpl userDao = new UserDAOImpl();
    @Override
    public List<UserDto> getAllUser() throws Exception {
        List<User> users = userDao.getAll();
        List<UserDto> userDto = new ArrayList<>();

        for(User user : users){
            userDto.add(new UserDto(
                    user.getId(),
                    user.getName(),
                    user.getAddress(),
                    user.getEmail(),
                    user.getPhone()
            ));
        }
        return userDto;
    }

    @Override
    public String generateNextUserId() throws Exception {
        return userDao.generateNextId();
    }

    @Override
    public boolean saveUser(UserDto userDto) throws Exception {
        return userDao.save(new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getAddress(),
                userDto.getEmail(),
                userDto.getPhone()
        ));
    }

    @Override
    public boolean DeleteUser(UserDto userDto) throws Exception {
        return userDao.delete(new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getAddress(),
                userDto.getEmail(),
                userDto.getPhone()
        ));
    }

    @Override
    public boolean updateUser(UserDto userDto) throws Exception {
        return userDao.update(new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getAddress(),
                userDto.getEmail(),
                userDto.getPhone()
        ));
    }

    @Override
    public UserDto SearchUser(String id) throws Exception {
        User user = userDao.Search(id);

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getAddress(),
                user.getEmail(),
                user.getPhone()
        );
    }
}
