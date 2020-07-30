package org.jwt.auth.user.controller;

import lombok.RequiredArgsConstructor;
import org.jwt.auth.user.model.UserDTO;
import org.jwt.auth.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDetails>> getAllUsers() {
        return new ResponseEntity(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> getUser(@RequestHeader String userId) {
        return new ResponseEntity(userService.getUser(userId), HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity<Void> createUser(@RequestHeader String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
