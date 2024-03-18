package org.example.bookworm.Dao.custom;

import org.example.bookworm.Dao.CrudDAO;
import org.example.bookworm.Entity.Admin;

public interface AdminDAO extends CrudDAO<Admin> {
    boolean check(Admin admin)throws Exception;
}
