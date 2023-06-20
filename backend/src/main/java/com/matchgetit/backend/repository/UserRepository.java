package com.matchgetit.backend.repository;

import com.matchgetit.backend.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MemberEntity, Long>, UserRepositoryCustom {
}
