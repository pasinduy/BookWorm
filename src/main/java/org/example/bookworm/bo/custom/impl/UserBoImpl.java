package org.example.bookworm.bo.custom.impl;

import org.example.bookworm.bo.custom.UserBO;
import org.example.bookworm.dao.custom.UserDAO;
import org.example.bookworm.dto.UserDto;
import org.example.bookworm.entity.User;
import org.example.bookworm.factory.DaoFactory;
import org.example.bookworm.util.Converter;
import org.example.bookworm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UserBoImpl implements UserBO {

    UserDAO dao = (UserDAO) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.USER);
    @Override
    public void saveUser(UserDto dto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try(session) {
            transaction.begin();
            dao.save(Converter.getInstance().toUserEntity(dto), session);
            transaction.commit();
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("User not added");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(UserDto dto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try(session){
            transaction.begin();
            dao.update(Converter.getInstance().toUserEntity(dto),session);
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("User not updated");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserDto> getAllTenants() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        try (session) {
            List <User> list = dao.getAll(session);
            if (list.size() > 0) {
                return list.stream().map(user -> Converter.getInstance().toUserDto(user)).collect(Collectors.toList());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Empty User list!");
    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        try(session) {
            String lastID = dao.generateNewId(session);
            if (lastID == null){
                return "U001";
            } else {
                String[] split = lastID.split("T");
                int lastDigit = Integer.parseInt(split[1]);
                lastDigit++;
                return (String.format("T%03d", lastDigit));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(String ID) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try(session){
            transaction.begin();
            dao.delete(ID, session);
            transaction.commit();
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("User not Deleted");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto viewUser(UserDto dto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        try(session) {
            User user = dao.search(dto.getUserID(),session);
            if(user != null) return Converter.getInstance().toUserDto(user);
            throw new RuntimeException("User Not Found");
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getTenantsCount() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        Integer count;
        try(session) {
            transaction.begin();
            count = dao.getUserCount(session);
            transaction.commit();
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
        return String.valueOf(count);
    }
}
