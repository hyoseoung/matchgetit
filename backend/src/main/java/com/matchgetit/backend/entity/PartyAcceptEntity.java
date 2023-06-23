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
    private int partyAcceptId;
    @Column(name="PARTY_LEADER_ID", nullable=false)
    private int partyLeaderId;
    @Enumerated(EnumType.STRING)
    @Column(name="AGREEMENT")
    private AcceptType agreement;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="OVER_TIME")
    private Date overTime;
    @Column(name="USER_ID")
    private int userId;
    @Column(name="USER_NAME")
    private String userName;
}
