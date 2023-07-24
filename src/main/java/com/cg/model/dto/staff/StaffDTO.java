package com.cg.model.dto.staff;

import com.cg.model.User;
import com.cg.model.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class StaffDTO {
    private Long id;

    private  String title;


    private String address;

    private String phone;

    private UserDTO user;
}
