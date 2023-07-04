package com.matchgetit.backend.entity;

import com.matchgetit.backend.constant.AcceptType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="PartyAccept")
@Data
@Setter
public class PartyAcceptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PA_ID")
    private Long partyAcceptId;
    @ManyToOne
    @JoinColumn(name = "PARTY_LEADER_ID", nullable=false)
    private MemberEntity partyLeader;
    @Enumerated(EnumType.STRING)
    @Column(name="AGREEMENT")
    private AcceptType agreement;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="OVER_TIME")
    private Date overTime;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable=false)
    private MemberEntity user;
}
