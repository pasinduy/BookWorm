package org.example.bookworm.dao.custom.impl;

import org.example.bookworm.dao.custom.BranchDAO;
import org.example.bookworm.entity.Branch;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public class BranchDaoImpl implements BranchDAO {
    @Override
    public ArrayList<Branch> getAll(Session session) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Branch data, Session session) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Branch data, Session session) throws SQLException, ClassNotFoundException {
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
    public Branch search(String Value, Session session) throws SQLException, ClassNotFoundException {
        return null;
    }
}
