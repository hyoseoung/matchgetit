package com.matchgetit.backend.repository;

import com.matchgetit.backend.constant.EmploymentStatus;
import com.matchgetit.backend.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<ManagerEntity, Long> {
    // 필요한 추가적인 쿼리 메소드들을 선언할 수 있음

    // Dashboard 사용
    long countByTeamIsNull();
    long countByTeamIsNotNull();
    long countByEmploymentStatusLike(EmploymentStatus employmentStatus);
}
