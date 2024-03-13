package org.example.bookworm.dao.custom.impl;

import org.example.bookworm.dao.custom.BorrowDAO;
import org.example.bookworm.entity.Borrow;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowDaoImpl implements BorrowDAO {
    @Override
    public ArrayList<Borrow> getAll(Session session) throws SQLException, ClassNotFoundException {
        Query query = session.createQuery("FROM Borrow", Borrow.class);
        List<Borrow> list = query.list();
        return (ArrayList<Borrow>) list;
    }

    @Override
    public boolean save(Borrow entity, Session session) throws SQLException, ClassNotFoundException {
        session.save(entity);
        return true;
    }

    @Override
    public boolean update(Borrow entity, Session session) throws SQLException, ClassNotFoundException {
        session.update(entity);
        return true;
    }

    @Override
    public boolean delete(String id, Session session) throws SQLException, ClassNotFoundException {
        Borrow borrow = new Borrow();
        borrow.setTransId(id);
        session.delete(borrow);
        return true;
    }

    @Override
    public String generateNewId(Session session) throws SQLException, ClassNotFoundException {
        Query query = session.createQuery("SELECT transId FROM Borrow ORDER BY transId DESC ");
        query.setMaxResults(1);
        List list = query.list();
        return (list.size() == 0) ? null : (String) list.get(0);
    }

    @Override
    public Borrow search(String id, Session session) throws SQLException, ClassNotFoundException {
        return session.get(Borrow.class, id);
    }

    @Override
    public Integer getCount(String Id, Session session) {
        String hql = "SELECT COUNT(*) FROM Borrow b";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("Title", Id);
        Long count = query.uniqueResult();
        return Math.toIntExact(count);
    }
}
