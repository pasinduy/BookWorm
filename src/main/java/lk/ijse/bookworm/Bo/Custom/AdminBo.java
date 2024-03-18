package lk.ijse.bookworm.Bo.Custom;

import lk.ijse.bookworm.Bo.SuperBo;
import lk.ijse.bookworm.Dto.AdminDto;

import java.util.List;

public interface AdminBo extends SuperBo {
     boolean saveCustomer(AdminDto adminDto) throws Exception;

     boolean check(AdminDto adminDto) throws Exception ;

     List<AdminDto> getAllAdmin() throws Exception ;


     String genarateNextAdminId() throws Exception;
}
