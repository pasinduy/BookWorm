package lk.ijse.bookworm.Bo.Custom.impl;

import lk.ijse.bookworm.Bo.Custom.BorrowBo;
import lk.ijse.bookworm.Dao.Custom.BorrowDAO;
import lk.ijse.bookworm.Dao.DAOFactory;

public class BorrowBoImpl implements BorrowBo {


    BorrowDAO borrowDaO = (BorrowDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.BORROW);
    @Override
    public String generateNextOrderDetailId() throws Exception {
        return borrowDaO.generateNextValue2();
    }
    @Override
    public String genarateNextBorrowId() throws Exception {
        return borrowDaO.generateNextId();
    }
}
