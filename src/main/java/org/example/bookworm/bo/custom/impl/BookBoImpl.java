package org.example.bookworm.bo.custom.impl;

import org.example.bookworm.bo.custom.BookBO;
import org.example.bookworm.dao.custom.BookDAO;
import org.example.bookworm.dto.BookDto;
import org.example.bookworm.entity.Book;
import org.example.bookworm.entity.User;
import org.example.bookworm.factory.DaoFactory;
import org.example.bookworm.util.Converter;
import org.example.bookworm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class BookBoImpl implements BookBO {

    BookDAO dao = (BookDAO) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.BOOK);

    @Override
    public void saveBook(BookDto dto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try(session){
            transaction.begin();
            dao.save(Converter.getInstance().toBookEntity(dto),session);
            transaction.commit();
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("Book not added");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBook(BookDto dto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try(session){
            transaction.begin();
            dao.update(Converter.getInstance().toBookEntity(dto),session);
            transaction.commit();
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("Book not added");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BookDto> getAllBooks() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        try (session) {
            List <Book> list = dao.getAll(session);
            if (list.size() > 0) {
                return list.stream().map(book -> Converter.getInstance().toBookDto(book)).collect(Collectors.toList());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Empty User list!");
    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        try(session) {
            String lastID = dao.generateNewId(session);
            if (lastID == null){
                return "B001";
            } else {
                String[] split = lastID.split("T");
                int lastDigit = Integer.parseInt(split[1]);
                lastDigit++;
                return (String.format("B%03d", lastDigit));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBook(String ID) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try(session){
            transaction.begin();
            dao.delete(ID, session);
            transaction.commit();
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("Book not Deleted");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookDto viewBook(BookDto dto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        try(session) {
            Book book = dao.search(dto.getBookId(),session);
            if(book != null) return Converter.getInstance().toBookDto(book);
            throw new RuntimeException("Book Not Found");
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
