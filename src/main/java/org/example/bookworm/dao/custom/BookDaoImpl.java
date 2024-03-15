package org.example.bookworm.dao.custom;

import org.example.bookworm.entity.Book;
import org.example.bookworm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class BookDaoImpl {

    public String generateNextId()throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<String> nativeQuery = session.createNativeQuery("SELECT id FROM book ORDER BY id DESC LIMIT 1");
        String id = nativeQuery.uniqueResult();
        transaction.commit();
        session.close();

        if (id!=null) {
            return splitId(id);
        }else{
            return splitId(null);
        }
    }

    private String splitId(String currentId)throws Exception {
        if(currentId != null) {
            String[] strings = currentId.split("B0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "B00"+id;
            }else {
                if (length < 3){
                    return "B0"+id;
                }else {
                    return "B"+id;
                }
            }
        }
        return "B001";
    }

    public List<Book> getAll()throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM book ");
        nativeQuery.addEntity(Book.class);
        List<Book> books = nativeQuery.getResultList();

        transaction.commit();
        session.close();

        return books;

    }

    public boolean Save(Book book)throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(book);

        transaction.commit();
        session.close();

        return true;
    }

    public boolean delete(Book book)throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(book);

        transaction.commit();
        session.close();

        return true;
    }

    public boolean update(Book book)throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(book);

        transaction.commit();
        session.close();

        return true;
    }

    public Book search(String id)throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Book book = session.get(Book.class,id);

        transaction.commit();
        session.close();

        return book;

    }
}
