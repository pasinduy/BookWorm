package org.example.bookworm.Dao.custom.Impl;

import org.example.bookworm.Dao.SuperDAO;
import org.example.bookworm.Config.FactoryConfiguration;
import org.example.bookworm.Dao.custom.QueryDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.management.Query;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    public List<Object[]> getTransaction(String user)throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<Object[]> nativeQuery = session.createNativeQuery("" +
                "SELECT u.name,bd.book_id,bk.title,bd.borrowDate,bd.returnDate,bd.status,b.user_id\n" +
                "FROM book_detils bd\n" +
                "JOIN borrow b on bd.borrow_borrowId = b.borrowId\n" +
                "join book bk on bd.book_id = bk.id\n" +
                "join user u on b.user_id = u.id\n" +
                "WHERE u.id = :user");

        nativeQuery.setParameter("user",user);

        List<Object[]> tran = nativeQuery.getResultList();


        transaction.commit();
        session.close();

        return tran;
    }

    public List<Object[]> getAllTimeOut() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<Object[]> nativeQuery = session.createNativeQuery("SELECT u.name,bd.book_id,bk.title,bd.borrowDate,bd.returnDate\n" +
                "FROM book_detils bd\n" +
                "         JOIN borrow b on bd.borrow_borrowId = b.borrowId\n" +
                "         JOIN user u on b.user_id = u.id\n" +
                "         JOIN book bk on bd.book_id = bk.id\n" +
                "WHERE bd.returnDate < CURRENT_DATE() and bd.status= \"Borrow\";");


        List<Object[]> tran = nativeQuery.getResultList();


        transaction.commit();
        session.close();

        return tran;

    }

    @Override
    public String generateNextId() throws Exception {
        return null;
    }

    @Override
    public List<Query> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(Query book) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Query book) throws Exception {
        return false;
    }

    @Override
    public boolean update(Query book) throws Exception {
        return false;
    }

    @Override
    public Query search(String id) throws Exception {
        return null;
    }
}
