package org.example.bookworm.Bo.custom;

import org.example.bookworm.Bo.SuperBo;

public interface BorrowBo extends SuperBo {
    String generateNextOrderDetailId() throws Exception;

    String genarateNextBorrowId() throws Exception;
}
