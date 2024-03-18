package org.example.bookworm.Bo.custom.Impl;

import org.example.bookworm.Bo.custom.BranchBo;
import org.example.bookworm.Dao.custom.Impl.AdminDAOImpl;
import org.example.bookworm.Dao.custom.Impl.BranchDAOImpl;
import org.example.bookworm.Dto.BranchDto;
import org.example.bookworm.Entity.Book;
import org.example.bookworm.Entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchBoImpl implements BranchBo {

    BranchDAOImpl branchDao = new BranchDAOImpl();
    AdminDAOImpl adminDao = new AdminDAOImpl();
    @Override
    public String generateNextId() throws Exception{
        return branchDao.generateNextId();
    }

    @Override
    public List<BranchDto> getAllBranch()throws Exception {
        List<Branch> branches = branchDao.getAll();
        List<BranchDto> branchDto = new ArrayList<>();

        for(Branch branch : branches){
            branchDto.add(new BranchDto(
                    branch.getId(),
                    branch.getName()
                    ,branch.getLocation()
                    ,branch.getAddress()
                    ,branch.getAdmin()
            ));
        }
        return branchDto;
    }

    @Override
    public boolean save(BranchDto branchDto) throws Exception{

        return branchDao.save(new Branch(
                branchDto.getId(),
                branchDto.getName(),
                branchDto.getLocation(),
                branchDto.getAddress(),
                branchDto.getAdmin(),
                new ArrayList<Book>()
        ));
    }

    @Override
    public boolean deleteBranch(BranchDto branchDto)throws Exception {
        return branchDao.delete(new Branch(
                branchDto.getId(),
                branchDto.getName(),
                branchDto.getLocation(),
                branchDto.getAddress(),
                branchDto.getAdmin(),
                new ArrayList<Book>()
        ));
    }

    @Override
    public boolean updateBranch(BranchDto branchDto) throws Exception{
        return branchDao.update(new Branch(
                branchDto.getId(),
                branchDto.getName(),
                branchDto.getLocation(),
                branchDto.getAddress(),
                branchDto.getAdmin(),
                new ArrayList<Book>()
        ));
    }
}