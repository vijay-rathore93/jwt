package org.jwt.auth.user.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jwt.auth.user.model.UserRequest;
import org.jwt.auth.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody UserRequest userRequest){
        return  null;
    }


}
