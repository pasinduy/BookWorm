package org.example.bookworm.bo.custom;

import org.example.bookworm.bo.SuperBo;

public interface BorrowBo extends SuperBo {
    String generateNextOrderDetailId() throws Exception;

    String genarateNextBorrowId() throws Exception;
}
