package lk.ijse.bookworm.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AdminDto {
    private String id;
    private String name;
    private String password;

}
