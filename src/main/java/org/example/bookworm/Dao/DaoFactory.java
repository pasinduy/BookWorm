package org.example.bookworm.Dao;

import org.example.bookworm.Dao.custom.*;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){}

    public static DaoFactory getDaoFactory(){return daoFactory == null? daoFactory = new DaoFactory() : daoFactory;}

    public enum DAOTypes{
        ADMIN, BOOK, BRANCH, BORROW, USER, QUERY, BOOKDETAILS
    }

    public SuperDao getDAO(DAOTypes types) {
        switch (types) {
            case ADMIN:
                return new AdminDaoImpl();
            case BOOK:
                return new BookDaoImpl();
            case BOOKDETAILS:
                return new BookDetailsDaoImpl();
            case BRANCH:
                return new BranchDaoImpl();
            case BORROW:
                return new BorrowDaoImpl();
            case USER:
                return  new UserDaoImpl();
            case QUERY:
                return new QueryDaoImpl();
            default:
                return null;
        }
    }
}
