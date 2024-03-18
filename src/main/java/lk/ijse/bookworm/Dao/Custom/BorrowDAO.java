package lk.ijse.bookworm.Dao.Custom;

import lk.ijse.bookworm.Dao.CrudDAO;
import lk.ijse.bookworm.Entity.Borrow;

public interface BorrowDAO extends CrudDAO<Borrow> {
//    String generateNextValue() throws Exception;


    String generateNextValue2()throws Exception ;

}
