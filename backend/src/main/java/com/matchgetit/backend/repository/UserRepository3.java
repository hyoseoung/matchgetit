package com.matchgetit.backend.repository;

import com.matchgetit.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository3 extends JpaRepository<User, Long>, UserRepositoryCustom {
}
