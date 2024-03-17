package org.example.bookworm.dao.custom;

import org.example.bookworm.dao.SuperDao;
import org.example.bookworm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

public class BorrowDaoImpl implements SuperDao {
    public String generateNextValue() throws Exception{
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
}
