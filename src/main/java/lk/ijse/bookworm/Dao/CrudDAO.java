package lk.ijse.bookworm.Dao;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    String generateNextId()throws Exception;

    List<T> getAll()throws Exception;

    boolean save(T book)throws Exception;

    boolean delete(T book)throws Exception;

    boolean update(T book)throws Exception;

    T search(String id)throws Exception;
}
