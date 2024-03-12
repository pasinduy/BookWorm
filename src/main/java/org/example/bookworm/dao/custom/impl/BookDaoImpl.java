package org.example.bookworm.dao.custom.impl;

import org.example.bookworm.dao.custom.BookDAO;
import org.example.bookworm.entity.Book;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookDaoImpl implements BookDAO {
    @Override
    public ArrayList<Book> getAll(Session session) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Book data, Session session) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Book data, Session session) throws SQLException, ClassNotFoundException {
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
    public Book search(String Value, Session session) throws SQLException, ClassNotFoundException {
        return null;
    }
}
