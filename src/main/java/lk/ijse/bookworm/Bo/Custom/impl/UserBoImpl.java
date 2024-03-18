package lk.ijse.bookworm.Bo.Custom.impl;

import lk.ijse.bookworm.Bo.Custom.UserBo;
import lk.ijse.bookworm.Dao.Custom.UserDAO;
import lk.ijse.bookworm.Dao.DAOFactory;
import lk.ijse.bookworm.Dto.UserDto;
import lk.ijse.bookworm.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {

    UserDAO userDao = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.USER);
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
        User user = userDao.search(id);

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getAddress(),
                user.getEmail(),
                user.getPhone()
        );
    }
}
