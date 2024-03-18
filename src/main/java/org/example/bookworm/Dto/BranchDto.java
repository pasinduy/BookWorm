package org.example.bookworm.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bookworm.Entity.Admin;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {
    private String id;
    private String name;
    private String location;
    private String Address;
    private Admin admin;
}