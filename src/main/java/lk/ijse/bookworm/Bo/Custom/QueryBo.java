package lk.ijse.bookworm.Bo.Custom;

import lk.ijse.bookworm.Bo.SuperBo;
import lk.ijse.bookworm.Dto.TimeOutDto;
import lk.ijse.bookworm.Dto.TransactionDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface QueryBo extends SuperBo {
    List<TransactionDto> getTransactions(String user) throws Exception;

    List<TimeOutDto> setAllTimeOut()throws Exception;

}
