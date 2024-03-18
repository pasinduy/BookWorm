package lk.ijse.bookworm.Dao.Custom.impl;

import lk.ijse.bookworm.Config.FactoryConfiguration;
import lk.ijse.bookworm.Dao.Custom.UserDAO;
import lk.ijse.bookworm.Entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> getAll() throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM user ");
        nativeQuery.addEntity(User.class);
        List<User> users = nativeQuery.getResultList();

        transaction.commit();
        session.close();

        return users;
    }
    @Override
    public String generateNextId() throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<String> nativeQuery = session.createNativeQuery("SELECT id FROM user ORDER BY id DESC LIMIT 1");
        String id = nativeQuery.uniqueResult();
        transaction.commit();
        session.close();

        if (id!=null) {
            return splitId(id);
        }else{
            return splitId(null);
        }
    }

    private String splitId(String currentId) throws Exception{
        if(currentId != null) {
            String[] strings = currentId.split("U0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "U00"+id;
            }else {
                if (length < 3){
                    return "U0"+id;
                }else {
                    return "U"+id;
                }
            }
        }
        return "U001";
    }
    @Override
    public boolean save(User user) throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(user);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(User user) throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(user);

        transaction.commit();
        session.close();
        return true;

    }
    @Override
    public boolean update(User user)throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(user);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User search(String id)throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class,id);

        transaction.commit();
        session.close();

        return user;
    }
}
