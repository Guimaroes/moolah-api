package com.moolah.user;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moolah.role.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);
  
  String findUsernameById(Long userId);
  
  String findPasswordById(Long userId);
  
  Set<Role> findRolesById(Long userId);
}
