package org.jwt.auth.user.service;

import lombok.RequiredArgsConstructor;
import org.jwt.auth.user.entity.UserLoginDetails;
import org.jwt.auth.user.model.UserDTO;
import org.jwt.auth.user.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public void createUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        //userRepo.save()
    }

    public List<UserDTO> getUsers() {
        List<UserLoginDetails> list = userRepo.findAll();
        return null;
    }

    public UserDTO getUser(String userId) {
        return userRepo.findByUserId(userId);
    }

    public void deleteUser(String userId) {
        userRepo.deleteByUserId(userId);
    }
}
