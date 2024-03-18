package org.example.bookworm.Dao.custom;

import org.example.bookworm.Dao.CrudDAO;
import org.example.bookworm.Entity.Borrow;

public interface BorrowDAO extends CrudDAO<Borrow> {
    String generateNextValue2()throws Exception ;
}
