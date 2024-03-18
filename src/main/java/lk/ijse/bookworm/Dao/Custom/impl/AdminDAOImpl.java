package lk.ijse.bookworm.Dao.Custom.impl;

import lk.ijse.bookworm.Config.FactoryConfiguration;
import lk.ijse.bookworm.Dao.Custom.AdminDAO;
import lk.ijse.bookworm.Entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public boolean save(Admin admin)throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(admin);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(Admin book) throws Exception {
        return false;
    }

    @Override
    public boolean update(Admin book) throws Exception {
        return false;
    }

    @Override
    public Admin search(String id) throws Exception {
        return null;
    }
    @Override
    public List<Admin> getAll()throws Exception{

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

//        Query query = session.createQuery("from Admin ");
//        List<Admin> admins = query.getResultList();

        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM admin");
        nativeQuery.addEntity(Admin.class);
        List<Admin> admins  = nativeQuery.getResultList();

        transaction.commit();
        session.close();

        return admins;
    }
    public boolean check(Admin admin)throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<String> query = session.createNativeQuery("select password from Admin where name = :name");
        query.setParameter("name",admin.getName());
        String pass= query.uniqueResult();

        System.out.println(pass);

        transaction.commit();
        session.close();


        if(pass.equals(admin.getPassword())){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public String generateNextId()throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<String> nativeQuery = session.createNativeQuery("SELECT id FROM admin ORDER BY id DESC LIMIT 1");
        String id = nativeQuery.uniqueResult();
        transaction.commit();
        session.close();

        if (id!=null) {
            return splitId(id);
        }else{
            return splitId(null);
        }
    }

    private String splitId(String currentId)throws Exception{
        if(currentId != null) {
            String[] strings = currentId.split("A0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "A00"+id;
            }else {
                if (length < 3){
                    return "A0"+id;
                }else {
                    return "A"+id;
                }
            }
        }
        return "A001";
    }
}
