package lk.ijse.bookworm.Bo.Custom.impl;

import lk.ijse.bookworm.Bo.Custom.QueryBo;
import lk.ijse.bookworm.Dao.Custom.QueryDao;
import lk.ijse.bookworm.Dao.Custom.impl.QueryDaoImpl;
import lk.ijse.bookworm.Dao.DAOFactory;
import lk.ijse.bookworm.Dto.TimeOutDto;
import lk.ijse.bookworm.Dto.TransactionDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class QueryBoImpl implements QueryBo {

    QueryDao queryDao = (QueryDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.QUERY);

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

    public List<TimeOutDto> setAllTimeOut() throws Exception {
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
