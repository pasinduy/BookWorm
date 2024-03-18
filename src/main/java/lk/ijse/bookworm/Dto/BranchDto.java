package lk.ijse.bookworm.Dto;

import lk.ijse.bookworm.Entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class BranchDto {
    private String id;
    private String name;
    private String location;
    private String Address;
    private Admin admin;
}
