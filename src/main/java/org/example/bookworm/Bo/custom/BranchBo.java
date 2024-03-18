package org.example.bookworm.Bo.custom;

import org.example.bookworm.Bo.SuperBo;
import org.example.bookworm.dto.BranchDto;

import java.util.List;

public interface BranchBo extends SuperBo {
    String generateNextId() throws Exception;

    List<BranchDto> getAllBranch()throws Exception;

    boolean save(BranchDto branchDto) throws Exception;

    boolean deleteBranch(BranchDto branchDto)throws Exception ;

    boolean updateBranch(BranchDto branchDto) throws Exception;
}
