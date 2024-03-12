package org.example.bookworm.dao.custom.impl;

import org.example.bookworm.dao.custom.AdminDAO;
import org.example.bookworm.entity.Admin;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDaoImpl implements AdminDAO {
    @Override
    public ArrayList<Admin> getAll(Session session) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Admin data, Session session) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Admin data, Session session) throws SQLException, ClassNotFoundException {
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
    public Admin search(String Value, Session session) throws SQLException, ClassNotFoundException {
        return null;
    }
}
