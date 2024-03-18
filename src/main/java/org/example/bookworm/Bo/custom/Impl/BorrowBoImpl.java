package org.example.bookworm.Bo.custom.Impl;

import org.example.bookworm.Bo.custom.BorrowBo;
import org.example.bookworm.Dao.custom.Impl.BorrowDAOImpl;

public class BorrowBoImpl implements BorrowBo {
    BorrowDAOImpl borrowDaO = new BorrowDAOImpl();
    @Override
    public String generateNextOrderDetailId() throws Exception {
        return borrowDaO.generateNextValue2();
    }
    @Override
    public String genarateNextBorrowId() throws Exception {
        return borrowDaO.generateNextValue();
    }
}
