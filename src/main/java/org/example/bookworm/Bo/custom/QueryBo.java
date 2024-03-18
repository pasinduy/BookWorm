package org.example.bookworm.Bo.custom;

import org.example.bookworm.Bo.SuperBo;
import org.example.bookworm.dto.TransactionDto;

import java.util.List;

public interface QueryBo extends SuperBo {
    List<TransactionDto> getTransactions(String user) throws Exception;
}
