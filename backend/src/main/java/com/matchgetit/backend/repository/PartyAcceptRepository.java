package com.matchgetit.backend.repository;

import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.entity.PartyAcceptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartyAcceptRepository extends JpaRepository<PartyAcceptEntity, Long> {
    List<PartyAcceptEntity> findByPartyLeaderId(int partyLeaderId);
    List<PartyAcceptEntity> findByUserId(int userId);
    PartyAcceptEntity findByPartyAcceptId(int partyAcceptId);
    PartyAcceptEntity findByUserIdAndPartyLeaderId(int userId,int partyLeaderId);

    void deleteByPartyLeaderId(int partyLeaderId);
}
