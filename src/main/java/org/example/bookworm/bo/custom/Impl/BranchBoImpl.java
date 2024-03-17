package org.example.bookworm.bo.custom.Impl;

import org.example.bookworm.bo.custom.BranchBo;
import org.example.bookworm.dao.custom.AdminDaoImpl;
import org.example.bookworm.dao.custom.BranchDaoImpl;
import org.example.bookworm.dto.BranchDto;
import org.example.bookworm.entity.Book;
import org.example.bookworm.entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchBoImpl implements BranchBo {

    BranchDaoImpl branchDao = new BranchDaoImpl();
    AdminDaoImpl adminDao = new AdminDaoImpl();
    @Override
    public String generateNextId() throws Exception{
        return branchDao.genatareNextId();
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