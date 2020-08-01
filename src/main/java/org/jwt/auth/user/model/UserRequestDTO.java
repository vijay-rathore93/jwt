package org.jwt.auth.user.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    @NotNull
    private String userName;
    @NotNull
    private String emailId;
    @NotNull
    private String password;
    private UserDTO userDTO;
    private Set<RoleRequestDTO> roleRequestDTO;
}
