package org.example.bookworm.Bo;

import org.example.bookworm.Bo.custom.Impl.*;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getBoFactory(){return boFactory == null? boFactory = new BoFactory() : boFactory;}

    public enum BOTypes{
        ADMIN, BOOK, BRANCH, BORROW, USER, QUERY
    }

    public SuperBo getBo(BOTypes types){
        switch (types){
            case ADMIN:
                return new AdminBoImpl();
            case BOOK:
                return new BookBoImpl();
            case BRANCH:
                return new BranchBoImpl();
            case BORROW:
                return new BorrowBoImpl();
            case USER:
                return new UserBoImpl();
            case QUERY:
                return new QueryBoImpl();
            default:
                return null;
        }
    }
}
