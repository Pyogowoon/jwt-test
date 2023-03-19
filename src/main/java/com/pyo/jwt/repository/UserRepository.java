package com.pyo.jwt.repository;

import com.pyo.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
     User findByUsername(String username);
}
