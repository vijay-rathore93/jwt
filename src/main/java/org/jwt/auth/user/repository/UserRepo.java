package org.jwt.auth.user.repository;

import org.jwt.auth.user.entity.UserLoginDetails;
import org.jwt.auth.user.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserLoginDetails, Long> {

    void deleteByUserId(String userId);

    UserDTO findByUserId(String userId);
}
