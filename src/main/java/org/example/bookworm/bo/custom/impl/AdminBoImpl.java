package org.example.bookworm.bo.custom.impl;


import org.example.bookworm.entity.Admin;
import org.hibernate.Session;
import org.example.bookworm.bo.custom.AdminBO;
import org.example.bookworm.dto.adminDto;
import org.example.bookworm.util.FactoryConfiguration;
import org.hibernate.Transaction;

import java.sql.SQLException;

public class AdminBoImpl implements AdminBO {

    Dao

    @Override
    public void updateBook(adminDto dto) {

    }

    @Override
    public adminDto viewCredentials(String text) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public adminDto getUserDetails(String userId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void saveUserDetails(adminDto dto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Admin admin = new Admin();
        admin.setId(admin.getId());
        admin.setPassword(admin.getPassword());

        Transaction transaction = session.getTransaction();
        try(session) {
            transaction.begin();

        }
    }
}
