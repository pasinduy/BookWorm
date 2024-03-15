package org.example.bookworm.dto.factory;

import org.example.bookworm.dao.SuperDAO;
import org.example.bookworm.dao.custom.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){}

    public static DaoFactory getDaoFactory(){return daoFactory == null? daoFactory = new DaoFactory() : daoFactory;}

    public enum DAOTypes{
        ADMIN, BOOK, BRANCH, BORROW, USER
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case ADMIN:
                return new AdminDaoImpl();
            case BOOK:
                return new BookDaoImpl();
            case BRANCH:
                return new BranchDaoImpl();
            case BORROW:
                return new BorrowDaoImpl();
            case USER:
                return new UserDaoImpl();
            default:
                return null;
        }
    }
}
