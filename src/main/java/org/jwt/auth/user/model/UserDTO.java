package org.jwt.auth.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jwt.auth.user.entity.ApplicationRole;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userName;
    private String password;
    private String fullName;
    private String emailId;
    private Long contact;
    private Set<ApplicationRole> applicationRoles;
}
