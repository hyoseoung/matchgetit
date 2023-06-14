package com.matchgetit.backend.repository;

import com.matchgetit.backend.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    // 필요한 추가적인 쿼리 메소드들을 선언할 수 있음
}
