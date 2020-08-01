package org.jwt.auth.user.service;


import lombok.RequiredArgsConstructor;
import org.jwt.auth.user.entity.ApplicationRole;
import org.jwt.auth.user.model.RoleRequestDTO;
import org.jwt.auth.user.repository.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;
    private final ModelMapper modelMapper;

    public void createRole(RoleRequestDTO roleRequestDTO){
        ApplicationRole applicationRole=modelMapper.map(roleRequestDTO, ApplicationRole.class);
        roleRepo.save(applicationRole);
    }

    public void deleteRole(String roleName){
        roleRepo.deleteByRoleName(roleName);
    }

    public RoleRequestDTO getRole(String roleName) {
       return  modelMapper.map(roleRepo.findByRoleName(roleName),RoleRequestDTO.class);
    }
}
