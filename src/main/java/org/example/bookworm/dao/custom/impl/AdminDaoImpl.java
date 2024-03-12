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
    public boolean save(Admin entity, Session session) throws SQLException, ClassNotFoundException {
        session.save(entity);
        return true;
    }

    @Override
    public boolean update(Admin entity, Session session) throws SQLException, ClassNotFoundException {
        session.update(entity);
        return true;
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
    public Admin search(String id, Session session) throws SQLException, ClassNotFoundException {
        try(session){
            return session.get(Admin.class, id);
        }
    }
}
