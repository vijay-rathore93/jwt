package org.jwt.auth.user.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "userLoginDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDetails {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long userLoginId;
    private String userName;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "applicationUserId")
    private ApplicationUser applicationUser;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "userLogin_Roles",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<ApplicationRole> applicationRoles;
}
