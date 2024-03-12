package org.example.bookworm.dao.custom.impl;

import org.example.bookworm.dao.custom.UserDAO;
import org.example.bookworm.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll(Session session) throws SQLException, ClassNotFoundException {
        Query query = session.createQuery("FROM User", User.class);
        List<User> list = query.list();
        return (ArrayList<User>) list;
    }

    @Override
    public boolean save(User entity, Session session) throws SQLException, ClassNotFoundException {
        session.save(entity);
        return true;
    }

    @Override
    public boolean update(User entity, Session session) throws SQLException, ClassNotFoundException {
        session.update(entity);
        return true;
    }

    @Override
    public boolean delete(String s, Session session) throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setUserID(s);
        session.delete(user);
        return true;
    }

    @Override
    public String generateNewId(Session session) throws SQLException, ClassNotFoundException {
        Query query = session.createQuery("SELECT userID FROM User ORDER BY userID DESC");
        query.setMaxResults(1);
        List list = query.list();
        return (list.size() == 0) ? null : (String) list.get(0);
    }

    @Override
    public User search(String id, Session session) throws SQLException, ClassNotFoundException {
        try(session){
            return session.get(User.class, id);
        }
    }

    @Override
    public Integer getUserCount(Session session) {
        String hql = "SELECT COUNT(userID) FROM User";
        Query<Long> query = session.createQuery(hql, Long.class);
        Long count = query.uniqueResult();
        return Math.toIntExact(count);
    }
}
