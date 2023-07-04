package com.matchgetit.backend.repository;

import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.entity.PartyEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyRepository extends JpaRepository<PartyEntity, Integer> {
    PartyEntity findByPartyId(Long partyId);
    PartyEntity findByPartyLeader(MemberEntity partyLeader);
    @EntityGraph(attributePaths = "members")
    @Query("SELECT p FROM PartyEntity p WHERE p = :party")
    PartyEntity findPartyWithMembers(@Param("party") PartyEntity party);
}

