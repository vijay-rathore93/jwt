package org.jwt.auth.user.controller;

import lombok.RequiredArgsConstructor;
import org.jwt.auth.user.model.RoleRequestDTO;
import org.jwt.auth.user.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/role")
    public ResponseEntity<Void> createRole(@RequestBody RoleRequestDTO roleRequestDTO) {
        roleService.createRole(roleRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/role")
    public ResponseEntity<RoleRequestDTO> getRole(@RequestHeader String roleName) {
        return new ResponseEntity<>(roleService.getRole(roleName), HttpStatus.OK);
    }

    @DeleteMapping("/role")
    public ResponseEntity<Void> deleteRole(@RequestHeader String roleName) {
        roleService.deleteRole(roleName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
