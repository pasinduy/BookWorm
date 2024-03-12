package org.example.bookworm.factory;

import org.example.bookworm.dao.SuperDAO;
import org.example.bookworm.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){return daoFactory == null? daoFactory = new DAOFactory() : daoFactory;}

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
