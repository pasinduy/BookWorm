package org.example.bookworm.bo.custom.impl;


import org.example.bookworm.dao.custom.AdminDAO;
import org.example.bookworm.entity.Admin;
import org.example.bookworm.factory.DaoFactory;
import org.hibernate.Session;
import org.example.bookworm.bo.custom.AdminBO;
import org.example.bookworm.dto.AdminDto;
import org.example.bookworm.util.FactoryConfiguration;
import org.hibernate.Transaction;

import java.sql.SQLException;

public class AdminBoImpl implements AdminBO {

    AdminDAO dao = (AdminDAO) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.ADMIN);


    @Override
    public void updateAdmin(AdminDto dto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Admin admin = new Admin();
        admin.setId(dto.getId());
        admin.setPassword(dto.getPassword());
        Transaction transaction = session.getTransaction();

        try(session){
            transaction.begin();
            dao.update(admin, session);
            transaction.commit();
        }catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public AdminDto viewCredentials(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Admin admin = dao.search(id, session);
        if (admin != null){
            AdminDto dto = new AdminDto();
            dto.setId(admin.getId());
            dto.setPassword(admin.getPassword());
            return dto;
        }
        throw new RuntimeException("User Not Found");
    }

    @Override
    public AdminDto getUserDetails(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Admin admin = dao.search(id, session);
        if (admin != null){
            AdminDto dto = new AdminDto();
            dto.setId(admin.getId());
            dto.setPassword(admin.getPassword());
            return dto;
        }
        throw new RuntimeException("Admin Not Found");
    }

    @Override
    public void saveUserDetails(AdminDto dto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Admin admin = new Admin();
        admin.setId(admin.getId());
        admin.setPassword(admin.getPassword());

        Transaction transaction = session.getTransaction();
        try(session) {
            transaction.begin();
            dao.save(admin, session);
            transaction.commit();;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
