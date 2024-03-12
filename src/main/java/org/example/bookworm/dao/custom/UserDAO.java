package org.example.bookworm.dao.custom;

import org.example.bookworm.dao.CrudDAO;
import org.example.bookworm.entity.User;
import org.hibernate.Session;

public interface UserDAO extends CrudDAO <User, String> {
    Integer getUserCount(Session session);
}
