package org.example.bookworm.bo.custom;

import org.example.bookworm.bo.SuperBo;
import org.example.bookworm.dto.AdminDto;

import java.util.List;

public interface AdminBo extends SuperBo {
    boolean saveCustomer(AdminDto adminDto) throws Exception;

    boolean check(AdminDto adminDto) throws Exception ;

    List<AdminDto> getAllAdmin() throws Exception ;

    String genarateNextAdminId() throws Exception;
}
