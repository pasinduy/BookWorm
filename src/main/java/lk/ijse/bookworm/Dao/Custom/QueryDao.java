package lk.ijse.bookworm.Dao.Custom;

import lk.ijse.bookworm.Config.FactoryConfiguration;
import lk.ijse.bookworm.Dao.SuperDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public interface QueryDao extends SuperDAO {
    public List<Object[]> getTransaction(String user)throws Exception;

    public List<Object[]> getAllTimeOut()throws Exception;
}
