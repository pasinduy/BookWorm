package lk.ijse.bookworm.Dao.Custom.impl;

import lk.ijse.bookworm.Config.FactoryConfiguration;
import lk.ijse.bookworm.Dao.Custom.BorrowDAO;
import lk.ijse.bookworm.Entity.Borrow;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class BorrowDaOImpl  implements BorrowDAO {
    @Override
    public String generateNextId() throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<String> nativeQuery = session.createNativeQuery("SELECT borrowId FROM Borrow ORDER BY borrowId DESC LIMIT 1");
        String id = nativeQuery.uniqueResult();
        transaction.commit();
        session.close();

        if (id != null) {
            return splitId(id);
        } else {
            return splitId(null);
        }
    }

    private String splitId(String currentId) throws Exception{
        if (currentId != null) {
            String[] strings = currentId.split("BO0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2) {
                return "BO00" + id;
            } else {
                if (length < 3) {
                    return "BO0" + id;
                } else {
                    return "BO" + id;
                }
            }
        }
        return "BO001";
    }

    public String generateNextValue2()throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<String> nativeQuery = session.createNativeQuery("SELECT bookDetailId FROM book_detils ORDER BY bookDetailId DESC LIMIT 1");
        String id = nativeQuery.uniqueResult();
        transaction.commit();
        session.close();

        if (id != null) {
            return splitId2(id);
        } else {
            return splitId2(null);
        }
    }

    private String splitId2(String currentId) throws Exception{
        if (currentId != null) {
            String[] strings = currentId.split("OD0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2) {
                return "OD00" + id;
            } else {
                if (length < 3) {
                    return "OD0" + id;
                } else {
                    return "OD" + id;
                }
            }
        }
        return "OD001";
    }


    @Override
    public List<Borrow> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(Borrow book) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Borrow book) throws Exception {
        return false;
    }

    @Override
    public boolean update(Borrow book) throws Exception {
        return false;
    }

    @Override
    public Borrow search(String id) throws Exception {
        return null;
    }
}
