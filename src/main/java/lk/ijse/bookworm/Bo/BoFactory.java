package lk.ijse.bookworm.Bo;

import lk.ijse.bookworm.Bo.Custom.impl.*;

public class BoFactory {
    public static  BoFactory boFactory;

    public BoFactory() {

    }

    public static BoFactory getBoFactory(){
        return (boFactory ==null) ? boFactory=new BoFactory() : boFactory;
    }
    public enum BOTypes{
        ADMIN,BOOK,BORROW,BRANCH,USER,QUERY
    }

    public SuperBo getBo(BOTypes boTypes){
        switch (boTypes){
            case ADMIN:
                return new AdminBoImpl();
            case BOOK:
                return new BookBoImpl();
            case BORROW:
                return new BorrowBoImpl();
            case BRANCH:
                return new BranchBoImpl();
            case USER:
                return new UserBoImpl();
            case QUERY:
                return new QueryBoImpl();
        }
        return null;
    }
}
