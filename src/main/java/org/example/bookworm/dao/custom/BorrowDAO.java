package org.example.bookworm.dao.custom;

import org.example.bookworm.dao.CrudDAO;
import org.example.bookworm.entity.Borrow;
import org.hibernate.Session;

public interface BorrowDAO extends CrudDAO<Borrow, String> {
    Integer getCount(String Id, Session session);
}
