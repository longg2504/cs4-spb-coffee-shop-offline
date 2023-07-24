package com.cg.model;

//import com.cg.model.dto.user.enums.ERole;
import com.cg.model.dto.role.RoleDTO;
import com.cg.model.enums.ERole;
import lombok.*;
import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    @OneToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    private List<User> users;

    public RoleDTO toRole() {
        return new RoleDTO()
                .setId(id)
                .setName(name)
                .setCode(code)
                ;

    }
}