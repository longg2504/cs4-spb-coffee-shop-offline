package com.cg.model;

import com.cg.model.dto.staff.StaffDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "staffs")
public class Staff extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private  String title;

    @Column
    private String address;

    @Column
    private String phone;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "staff")
    @JsonIgnore
    private List<Order> orders;


    public StaffDTO toStaffDTO() {
        return new StaffDTO()
                .setId(id)
                .setTitle(title)
                .setAddress(address)
                .setPhone(phone)
                .setUser(user.toUserDTO())
                ;
    }


}
