package org.example.bookworm.dao.custom.impl;

import org.example.bookworm.dao.custom.BorrowDAO;
import org.example.bookworm.entity.Borrow;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public class BorrowDaoImpl implements BorrowDAO {
    @Override
    public ArrayList<Borrow> getAll(Session session) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Borrow data, Session session) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Borrow data, Session session) throws SQLException, ClassNotFoundException {
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
    public Borrow search(String Value, Session session) throws SQLException, ClassNotFoundException {
        return null;
    }
}
