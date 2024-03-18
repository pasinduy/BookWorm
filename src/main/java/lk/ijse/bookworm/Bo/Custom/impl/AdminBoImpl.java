package lk.ijse.bookworm.Bo.Custom.impl;

import lk.ijse.bookworm.Bo.Custom.AdminBo;
import lk.ijse.bookworm.Dao.Custom.AdminDAO;
import lk.ijse.bookworm.Dao.DAOFactory;
import lk.ijse.bookworm.Dto.AdminDto;
import lk.ijse.bookworm.Entity.Admin;
import lk.ijse.bookworm.Entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class AdminBoImpl implements AdminBo {

    AdminDAO adminDao = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.ADMIN);

    @Override
    public boolean saveCustomer(AdminDto adminDto) throws Exception {
        return adminDao.save(new Admin(adminDto.getId(),adminDto.getName(),adminDto.getPassword(),new ArrayList<Branch>()));
    }

    @Override
    public boolean check(AdminDto adminDto) throws Exception {
        System.out.println("cheak "+adminDto.getName());
        return adminDao.check(new Admin(adminDto.getName(),adminDto.getPassword(),new ArrayList<Branch>()));
    }
    @Override
    public List<AdminDto> getAllAdmin() throws Exception {
        List<Admin> admins = adminDao.getAll();

        List<AdminDto> adminDtoList = new ArrayList<>();

        for(Admin admin :admins){
            adminDtoList.add(new AdminDto(admin.getId(),admin.getName(),admin.getPassword()));
        }
        return adminDtoList;
    }

    @Override
    public String genarateNextAdminId() throws Exception {
        return adminDao.generateNextId();
    }
}
