package org.jwt.auth.user.repository;

import org.jwt.auth.user.entity.UserLoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserLoginDetails, Long> {
}
