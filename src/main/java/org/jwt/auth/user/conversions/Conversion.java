package org.jwt.auth.user.conversions;

import lombok.RequiredArgsConstructor;
import org.jwt.auth.user.entity.ApplicationRole;
import org.jwt.auth.user.entity.ApplicationUser;
import org.jwt.auth.user.entity.UserLoginDetails;
import org.jwt.auth.user.model.UserRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Conversion {
    private final ModelMapper modelMapper;


    public UserLoginDetails convertDTOToEntity(UserRequestDTO userRequestDTO) {
        UserLoginDetails userLoginDetails = modelMapper.map(userRequestDTO, UserLoginDetails.class);
        Set<ApplicationRole> applicationRole = userRequestDTO.getRoleRequestDTO().stream()
                .map(role -> modelMapper.map(role, ApplicationRole.class))
                .collect(Collectors.toSet());
        userLoginDetails.setApplicationRoles(applicationRole);
        if(userRequestDTO.getUserDTO()!=null) {
            ApplicationUser applicationUser = modelMapper.map(userRequestDTO.getUserDTO(), ApplicationUser.class);

            userLoginDetails.setApplicationUser(applicationUser);
        }
        userLoginDetails.setIsActive(true);
        return userLoginDetails;
    }



}
