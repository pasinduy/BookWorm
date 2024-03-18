package org.example.bookworm.Dao;

import org.example.bookworm.Dao.custom.Impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){}

    public static DaoFactory getDaoFactory(){return daoFactory == null? daoFactory = new DaoFactory() : daoFactory;}

    public enum DAOTypes{
        ADMIN, BOOK, BRANCH, BORROW, USER, QUERY
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case ADMIN:
                return new AdminDAOImpl();
            case BOOK:
                return new BookDAOImpl();
            case BRANCH:
                return new BranchDAOImpl();
            case BORROW:
                return new BorrowDAOImpl();
            case USER:
                return  new UserDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
