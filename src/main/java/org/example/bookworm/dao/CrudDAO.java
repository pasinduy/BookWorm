package org.example.bookworm.dao;

import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T,ID> extends SuperDAO {
    public ArrayList<T> getAll(Session session) throws SQLException, ClassNotFoundException;

    public boolean save(T data, Session session) throws SQLException, ClassNotFoundException;

    public boolean update(T data, Session session) throws SQLException, ClassNotFoundException;

    public boolean exits(ID id) throws SQLException, ClassNotFoundException;

    public boolean delete(ID id, Session session) throws SQLException, ClassNotFoundException;

    public ID generateNewId(Session session) throws SQLException, ClassNotFoundException;

    public T search(ID Value, Session session) throws SQLException, ClassNotFoundException;
}
