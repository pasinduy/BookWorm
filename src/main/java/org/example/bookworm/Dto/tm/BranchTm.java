package org.example.bookworm.Dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchTm {
    private String id;
    private String name;
    private String location;
    private String Address;
    private String Admin;
}
