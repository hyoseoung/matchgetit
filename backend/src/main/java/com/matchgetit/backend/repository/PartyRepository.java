package com.matchgetit.backend.repository;

import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.entity.PartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyRepository extends JpaRepository<PartyEntity, Integer> {
    PartyEntity findByPartyId(int partyId);
    PartyEntity findByPartyLeader(int partyLeader);
    @Query("SELECT m.partyEntity FROM MemberEntity m WHERE m.userId = :memberId")
    PartyEntity findByMemberId(@Param("memberId") int memberId);
    @Query("SELECT m FROM MemberEntity m WHERE m.partyEntity = :party")
    List<MemberEntity> findPartyMembers(@Param("party") PartyEntity party);
}

