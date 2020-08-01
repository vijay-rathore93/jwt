package org.jwt.auth.user.repository;

import org.jwt.auth.user.entity.ApplicationUser;
import org.jwt.auth.user.entity.UserLoginDetails;
import org.jwt.auth.user.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserLoginDetails, Long> {

    void deleteByUserName(String userId);

    UserDTO findByUserName(String userId);

    Optional<UserLoginDetails> findByEmailId(String usernameOrEmail);
}
