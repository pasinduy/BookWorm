package org.example.bookworm.factory;

import org.example.bookworm.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory(){return boFactory == null? boFactory = new BOFactory() : boFactory;}

    public enum BOTypes{
        ADMIN, BOOK, BRANCH, BORROW, USER
    }

    public SuperBO getBO(BOTypes types){
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
            default:
                return null;
        }
    }
}
