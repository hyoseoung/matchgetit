package com.matchgetit.backend.repository;

import com.matchgetit.backend.entity.RankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankRepository extends JpaRepository<RankEntity, String> {
    @Query(value="SELECT GROUP_ID, USER_ID, NAME, RATING, WIN, LOSE,RANK_ID, VIC_RATING,RANK() OVER (ORDER BY RATING DESC) RANK  FROM RANK_BRD ORDER BY RATING DESC", nativeQuery = true)
    List<RankEntity> findRankAll();


}