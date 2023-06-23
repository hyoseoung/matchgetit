package com.matchgetit.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PARTY")
@Data
@Setter
public class PartyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PARTY_ID")
    private int partyId;

    @Column(name = "PARTY_LEADER_ID")
    private int partyLeader;

    @Column(name = "APPLICATION_DATE")
    private Date applicationDate;

    @Column(name = "APPLICATION_TIME")
    private String applicationTime;

    @Column(name = "LOCATION_X")
    private String locationX;

    @Column(name = "LOCATION_Y")
    private String locationY;

    @Column(name = "STADIUM_ID")
    private int stadiumId;
}



