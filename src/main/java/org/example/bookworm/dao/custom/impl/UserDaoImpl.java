package org.example.bookworm.dao.custom.impl;

import org.example.bookworm.dao.custom.UserDAO;
import org.example.bookworm.entity.User;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll(Session session) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(User data, Session session) throws SQLException, ClassNotFoundException {
        session.save(entity);
    }

    @Override
    public boolean update(User data, Session session) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exits(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s, Session session) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId(Session session) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public User search(String Value, Session session) throws SQLException, ClassNotFoundException {
        return null;
    }
}
