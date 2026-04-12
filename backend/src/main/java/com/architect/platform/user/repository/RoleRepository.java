package com.architect.platform.user.repository;

import com.architect.platform.user.entity.Role;
import com.architect.platform.user.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);

}
