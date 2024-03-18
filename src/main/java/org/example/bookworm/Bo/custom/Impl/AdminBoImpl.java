package org.example.bookworm.Bo.custom.Impl;

import org.example.bookworm.Bo.custom.AdminBo;
import org.example.bookworm.Dao.custom.Impl.AdminDAOImpl;
import org.example.bookworm.Dto.AdminDto;
import org.example.bookworm.Entity.Admin;
import org.example.bookworm.Entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class AdminBoImpl implements AdminBo {
    AdminDAOImpl adminDao = new AdminDAOImpl();

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