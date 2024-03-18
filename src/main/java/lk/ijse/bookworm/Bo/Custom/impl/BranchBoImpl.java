package lk.ijse.bookworm.Bo.Custom.impl;

import lk.ijse.bookworm.Bo.Custom.BranchBo;
import lk.ijse.bookworm.Dao.Custom.BranchDAO;
import lk.ijse.bookworm.Dao.DAOFactory;
import lk.ijse.bookworm.Dto.BranchDto;
import lk.ijse.bookworm.Entity.Book;
import lk.ijse.bookworm.Entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchBoImpl implements BranchBo {
    BranchDAO branchDao = (BranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.BRANCH);

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
