package com.matchgetit.backend.repository;

import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.entity.PartyAcceptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyAcceptRepository extends JpaRepository<PartyAcceptEntity, Long> {
    List<PartyAcceptEntity> findByPartyLeader(MemberEntity partyLeader);
    List<PartyAcceptEntity> findByUser(MemberEntity user);
    PartyAcceptEntity findByPartyAcceptId(Long partyAcceptId);
    PartyAcceptEntity findByUserAndPartyLeader(MemberEntity user,MemberEntity partyLeader);

    void deleteByPartyLeader(MemberEntity partyLeader);
    void deleteByPartyLeaderAndUser(MemberEntity partyLeader,MemberEntity user);
}
