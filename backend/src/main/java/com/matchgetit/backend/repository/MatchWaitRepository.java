package com.matchgetit.backend.repository;


import com.matchgetit.backend.entity.MatchWaitEntity;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.entity.StadiumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MatchWaitRepository extends JpaRepository<MatchWaitEntity, Long> {
    MatchWaitEntity findByMember(MemberEntity member);

    List<MatchWaitEntity> findByStadiumAndParty_ApplicationTimeAndParty_ApplicationDate(
            StadiumEntity stadium, String applicationTime, Date applicationDate);
}
