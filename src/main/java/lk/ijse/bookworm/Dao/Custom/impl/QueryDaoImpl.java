package lk.ijse.bookworm.Dao.Custom.impl;

import lk.ijse.bookworm.Config.FactoryConfiguration;
import lk.ijse.bookworm.Dao.Custom.QueryDao;
import lk.ijse.bookworm.Entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;
import java.util.Objects;

public class QueryDaoImpl implements QueryDao {
    @Override
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

        List<Object[]> trans = nativeQuery.getResultList();

        transaction.commit();
        session.close();

        return trans;
    }

    @Override
    public List<Object[]> getAllTimeOut()throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<Object[]> nativeQuery = session.createNativeQuery("SELECT u.name,bd.book_id,bk.title,bd.borrowDate,bd.returnDate\n" +
                "FROM book_detils bd\n" +
                "         JOIN borrow b on bd.borrow_borrowId = b.borrowId\n" +
                "         JOIN user u on b.user_id = u.id\n" +
                "         JOIN book bk on bd.book_id = bk.id\n" +
                "WHERE bd.returnDate < CURRENT_DATE() and bd.status= \"Borrow\";");


        List<Object[]> trans = nativeQuery.getResultList();

        transaction.commit();
        session.close();

        return trans;

    }
}
