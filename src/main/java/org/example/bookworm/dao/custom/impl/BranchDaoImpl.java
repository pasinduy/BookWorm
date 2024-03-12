package org.example.bookworm.dao.custom.impl;

import org.example.bookworm.dao.custom.BranchDAO;
import org.example.bookworm.entity.Branch;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDaoImpl implements BranchDAO {
    @Override
    public ArrayList<Branch> getAll(Session session) throws SQLException, ClassNotFoundException {
        try(session){
            String sql = "FROM Branch";
            Query query = session.createQuery(sql);
            List list = query.list();
            return (ArrayList<Branch>) list;
        }
    }

    @Override
    public boolean save(Branch entity, Session session) throws SQLException, ClassNotFoundException {
        session.save(entity);
        return true;
    }

    @Override
    public boolean update(Branch entity, Session session) throws SQLException, ClassNotFoundException {
        session.update(entity);
        return true;
    }


    @Override
    public boolean delete(String s, Session session) throws SQLException, ClassNotFoundException {
        Branch branch = session.get(Branch.class, s);
        if (branch != null){
            session.delete(branch);
            return true;
        }
        return false;
    }

    @Override
    public String generateNewId(Session session) throws SQLException, ClassNotFoundException {
        try(session){
            Query query = session.createQuery("SELECT branchId FROM Branch ORDER BY branchId DESC ");
            query.setMaxResults(1);
            List list = query.list();
            return (list.size() == 0) ? null : (String) list.get(0);
        }
    }

    @Override
    public Branch search(String id, Session session) throws SQLException, ClassNotFoundException {
        try(session){
            return session.get(Branch.class, id);
        }
    }
}
