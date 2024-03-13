package org.example.bookworm.bo.custom;

import org.example.bookworm.bo.SuperBO;
import org.example.bookworm.dto.BorrowDto;
import org.example.bookworm.dto.BranchDto;

import java.util.List;

public interface BranchBO extends SuperBO {
    void saveBranch(BranchDto dto);

    void updateBranch(BranchDto dto);

    List<BranchDto> getAllBranch();

    String getLastId();

    void deleteBranch(String branchId);

    BranchDto viewBranch(BranchDto dto);
}
