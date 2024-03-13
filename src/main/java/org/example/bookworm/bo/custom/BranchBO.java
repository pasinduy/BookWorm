package org.example.bookworm.bo.custom;

import org.example.bookworm.bo.SuperBO;
import org.example.bookworm.dto.BorrowDto;
import org.example.bookworm.dto.BranchDto;

import java.util.List;

public interface BranchBO extends SuperBO {
    void saveTenant(BranchDto dto);

    void updateTenant(BranchDto dto);

    List<BranchDto> getAllBooks();

    String getLastId();

    void deleteTenant(String branchId);

    BranchDto viewTenant(BranchDto dto);
}
