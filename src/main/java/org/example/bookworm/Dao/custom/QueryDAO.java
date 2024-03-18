package org.example.bookworm.Dao.custom;

import org.example.bookworm.Dao.CrudDAO;

import javax.management.Query;
import java.util.List;

public interface QueryDAO extends CrudDAO<Query> {
    public List<Object[]> getTransaction(String user)throws Exception;

    public List<Object[]> getAllTimeOut()throws Exception;
}
