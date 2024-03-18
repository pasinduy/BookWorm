package lk.ijse.bookworm.Bo.Custom;

import lk.ijse.bookworm.Bo.SuperBo;
import lk.ijse.bookworm.Dto.BranchDto;
import lk.ijse.bookworm.Entity.Book;
import lk.ijse.bookworm.Entity.Branch;

import java.util.ArrayList;
import java.util.List;

public interface BranchBo extends SuperBo {
    String generateNextId() throws Exception;

    List<BranchDto> getAllBranch()throws Exception;

    boolean save(BranchDto branchDto) throws Exception;

    boolean deleteBranch(BranchDto branchDto)throws Exception ;

    boolean updateBranch(BranchDto branchDto) throws Exception;
}
