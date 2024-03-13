package org.example.bookworm.bo.custom.impl;

import org.example.bookworm.bo.custom.BranchBO;
import org.example.bookworm.dao.custom.BranchDAO;
import org.example.bookworm.dto.BranchDto;
import org.example.bookworm.entity.Branch;
import org.example.bookworm.entity.User;
import org.example.bookworm.factory.DaoFactory;
import org.example.bookworm.util.Converter;
import org.example.bookworm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class BranchBoImpl implements BranchBO {

    BranchDAO dao = (BranchDAO) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.BRANCH);

    @Override
    public void saveBranch(BranchDto dto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try(session) {
            transaction.begin();
            dao.save(Converter.getInstance().toBranchEntity(dto), session);
            transaction.commit();
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("Branch not added");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBranch(BranchDto dto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try(session) {
            transaction.begin();
            dao.update(Converter.getInstance().toBranchEntity(dto), session);
            transaction.commit();
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("Branch not added");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BranchDto> getAllBranch() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        try (session) {
            List <Branch> list = dao.getAll(session);
            if (list.size() > 0) {
                return list.stream().map(branch -> Converter.getInstance().toBranchDto(branch)).collect(Collectors.toList());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Empty Branch list!");
    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        try(session) {
            String lastID = dao.generateNewId(session);
            if (lastID == null){
                return "R001";
            } else {
                String[] split = lastID.split("R");
                int lastDigit = Integer.parseInt(split[1]);
                lastDigit++;
                return (String.format("R%03d", lastDigit));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBranch(String ID) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try(session){
            transaction.begin();
            dao.delete(ID, session);
            transaction.commit();
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("Branch not Deleted");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BranchDto viewBranch(BranchDto dto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        try(session) {
            Branch branch = dao.search(dto.getBranchId(),session);
            if(branch != null) return Converter.getInstance().toBranchDto(branch);
            throw new RuntimeException("Branch Not Found");
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
