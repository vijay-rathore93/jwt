package org.jwt.auth.user.repository;

import org.jwt.auth.user.entity.ApplicationRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<ApplicationRole,Long> {
    void deleteByRoleName(String roleName);

    ApplicationRole findByRoleName(String roleName);
}
