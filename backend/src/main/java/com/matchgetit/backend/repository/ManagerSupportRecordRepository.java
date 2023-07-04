package com.matchgetit.backend.repository;

import com.matchgetit.backend.entity.ManagerSupportRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerSupportRecordRepository extends JpaRepository<ManagerSupportRecordEntity, Long> {
    // 추가적인 메서드 또는 쿼리가 필요한 경우 여기에 작성할 수 있습니다.
}
