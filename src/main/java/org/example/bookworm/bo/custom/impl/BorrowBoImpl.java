package org.example.bookworm.bo.custom.impl;

import org.example.bookworm.bo.custom.BorrowBO;
import org.example.bookworm.dao.custom.BookDAO;
import org.example.bookworm.dao.custom.BorrowDAO;
import org.example.bookworm.dao.custom.BranchDAO;
import org.example.bookworm.dao.custom.UserDAO;
import org.example.bookworm.dto.BorrowDto;
import org.example.bookworm.entity.Borrow;
import org.example.bookworm.factory.DaoFactory;
import org.example.bookworm.util.Converter;
import org.example.bookworm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowBoImpl implements BorrowBO {

    BorrowDAO borrowDao = (BorrowDAO) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.BORROW);
    UserDAO userDAO = (UserDAO) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.USER);
    BookDAO bookDAO = (BookDAO) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.BOOK);
    BranchDAO branchDAO = (BranchDAO) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.BRANCH);


    @Override
    public BorrowDto viewBorrow(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Borrow entity = null;
        try{
            entity = borrowDao.search(id, session);
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }if (entity != null) {
            return Converter.getInstance().toBorrowDto(entity);
        }
        throw new RuntimeException("Transaction not found!");
    }

    @Override
    public void saveBorrow(BorrowDto borrow) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try(session){
            transaction.begin();
            borrowDao.save(Converter.getInstance().toBorrowEntity(borrow), session);
            transaction.commit();
        }catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBorrow(BorrowDto borrow) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try(session){
            transaction.begin();
            borrowDao.update(Converter.getInstance().toBorrowEntity(borrow), session);
            transaction.commit();
        }catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBorrow(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try(session) {
            transaction.begin();
            borrowDao.delete(id, session);
            transaction.commit();
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer getBorrowCount(String borrowId) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        Integer count;

        try(session) {
            transaction.begin();
            count = borrowDao.getCount(borrowId, session);
            transaction.commit();
        }catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
        return count;
    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        String lastId = null;
        try(session){
            lastId = borrowDao.generateNewId(session);
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(lastId == null){
            return "T001";
        } else {
            String[] split = lastId.split("[T]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            return  (String.format("T%03d", lastDigits));
        }
    }

    @Override
    public List<BorrowDto> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        List<Borrow> list = null;
        try {
            list = borrowDao.getAll(session);
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (list.size() > 0) {
            return list.stream().map(borrow -> Converter.getInstance().toBorrowDto(borrow)).collect(Collectors.toList());
        }
        throw new RuntimeException("Empty Transaction List");
    }
}
