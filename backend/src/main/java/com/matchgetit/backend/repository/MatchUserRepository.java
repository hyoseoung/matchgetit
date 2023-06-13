package com.matchgetit.backend.repository;

import com.matchgetit.backend.entity.MatchUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchUserRepository extends JpaRepository<MatchUserEntity, Long> {
    MatchUserEntity findByEmail(String email);
}

