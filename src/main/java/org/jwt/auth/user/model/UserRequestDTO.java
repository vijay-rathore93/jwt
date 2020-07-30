package org.jwt.auth.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String userName;
    private String password;
    private UserDTO userDTO;
    private Set<RoleRequestDTO> roleRequestDTO;
}
