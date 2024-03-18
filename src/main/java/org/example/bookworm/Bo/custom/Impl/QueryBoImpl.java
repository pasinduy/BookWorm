package org.example.bookworm.Bo.custom.Impl;

import org.example.bookworm.Bo.custom.QueryBo;
import org.example.bookworm.Dao.custom.QueryDaoImpl;
import org.example.bookworm.Dto.TimeOutDto;
import org.example.bookworm.Dto.TransactionDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueryBoImpl implements QueryBo {
    QueryDaoImpl queryDao = new QueryDaoImpl();
    public List<TransactionDto> getTransactions(String user) throws Exception{
        List<Object[]> objects= queryDao.getTransaction(user);

        ArrayList<TransactionDto> trans = new ArrayList<>();


        for(Object[] ob : objects){
            trans.add(new TransactionDto(
                    (String) ob[0],
                    (String) ob[1],
                    (String) ob[2],
                    (Date) ob[3],
                    (Date) ob[4],
                    (String) ob[5]
            ));
        }
        return trans;

    }

    public List<TimeOutDto> setAllTimeOut() {
        List<Object[]> objects = queryDao.getAllTimeOut();

        ArrayList<TimeOutDto> trans = new ArrayList<>();


        for(Object[] ob : objects){
            trans.add(new TimeOutDto(
                    (String) ob[0],
                    (String) ob[1],
                    (String) ob[2],
                    (Date) ob[3],
                    (Date) ob[4]
            ));
        }
        return trans;

    }
}
