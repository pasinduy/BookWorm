package org.example.bookworm.dao.custom;

import org.example.bookworm.entity.Branch;
import org.example.bookworm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class BranchDaoImpl {

    public String genatareNextId()throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<String> nativeQuery = session.createNativeQuery("SELECT id FROM branch ORDER BY id DESC LIMIT 1");
        String id = nativeQuery.uniqueResult();
        transaction.commit();
        session.close();

        if (id != null) {
            return splitId(id);
        } else {
            return splitId(null);
        }
    }

    private String splitId(String currentId)throws Exception{
        if (currentId != null) {
            String[] strings = currentId.split("BR0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2) {
                return "BR00" + id;
            } else {
                if (length < 3) {
                    return "BR0" + id;
                } else {
                    return "BR" + id;
                }
            }
        }
        return "BR001";
    }

    public List<Branch> getAll()throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM branch");
        nativeQuery.addEntity(Branch.class);
        List<Branch> branches = nativeQuery.getResultList();


        transaction.commit();
        session.close();

        return branches;
    }

    public boolean save(Branch branch)throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(branch);

        transaction.commit();
        session.close();

        return true;
    }

    public boolean delete(Branch branch)throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(branch);

        transaction.commit();
        session.close();

        return true;
    }

    public boolean update(Branch branch)throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(branch);

        transaction.commit();
        session.close();

        return true;
    }
}
