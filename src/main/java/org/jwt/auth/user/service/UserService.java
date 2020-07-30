package org.jwt.auth.user.service;

import lombok.RequiredArgsConstructor;
import org.jwt.auth.user.entity.UserLoginDetails;
import org.jwt.auth.user.model.UserDTO;
import org.jwt.auth.user.model.UserRequestDTO;
import org.jwt.auth.user.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    public void createUser(UserRequestDTO userRequestDTO) {
        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        UserLoginDetails userLoginDetails = modelMapper.map(userRequestDTO, UserLoginDetails.class);
        userRepo.save(userLoginDetails);
    }

    public List<UserDTO> getUsers() {
        return userRepo.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    public UserDTO getUser(String userId) {
        return Arrays.asList(userRepo.findByUserId(userId)).stream().map(user -> modelMapper.map(user, UserDTO.class)).findAny().get();
    }

    public void deleteUser(String userId) {
        userRepo.deleteByUserId(userId);
    }
}
