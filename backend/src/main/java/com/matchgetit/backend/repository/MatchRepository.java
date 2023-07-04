package com.matchgetit.backend.repository;

import com.matchgetit.backend.constant.GameType;
import com.matchgetit.backend.entity.MatchEntity;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.entity.PartyEntity;
import com.matchgetit.backend.entity.StadiumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity,Long> {
    List<MatchEntity> findAllByOrderByCycleDesc();//사이클이 큰 순으로 리턴(가중치)
    void deleteMatchEntitiesByParty(PartyEntity partyEntity);

    MatchEntity findByMember(MemberEntity member);

    List<MatchEntity> findByStadiumAndParty_ApplicationTimeAndParty_ApplicationDate_AndParty_GameType(
            StadiumEntity stadium, String applicationTime, Date applicationDate, String gameType);
}
