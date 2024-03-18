package lk.ijse.bookworm.Bo.Custom;

import lk.ijse.bookworm.Bo.SuperBo;

public interface BorrowBo extends SuperBo {
    String generateNextOrderDetailId() throws Exception;

    String genarateNextBorrowId() throws Exception;
}
