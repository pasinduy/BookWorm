package org.example.bookworm.bo.custom.Impl;

import org.example.bookworm.bo.custom.BorrowBo;
import org.example.bookworm.dao.custom.BorrowDaoImpl;

public class BorrowBoImpl implements BorrowBo {
    BorrowDaoImpl borrowDaO = new BorrowDaoImpl();
    @Override
    public String generateNextOrderDetailId() throws Exception {
        return borrowDaO.generateNextValue2();
    }
    @Override
    public String genarateNextBorrowId() throws Exception {
        return borrowDaO.generateNextValue();
    }
}
