package com.matchgetit.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
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
    private Long partyId;
    @ManyToOne
    @JoinColumn(name = "PARTY_LEADER_ID")
    private MemberEntity partyLeader;

    @Column(name="PARTY_COUNT")
    private Long count;

    @Column(name = "APPLICATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date applicationDate;//사용자 선택 날짜

    @Column(name = "APPLICATION_TIME")
    private String applicationTime;//사용자 선택 시간

    @Column(name="ADDRESS")
    private String address;

    @Column(name = "LOCATION_X")
    private String locationX;

    @Column(name = "LOCATION_Y")
    private String locationY;

    @Column(name="PARTY_APPLICATION_DATE")
    private Date partyApplicationDate;//파티 결성 시간

    @Column(name="PARTY_RATING_AVG")
    private Long partyRatingAvg;

    @Column(name="GAME_TYPE")
    private String gameType;
    @JsonIgnore
    @JsonIgnoreProperties
    @JsonManagedReference
    @OneToMany(mappedBy = "party", fetch = FetchType.EAGER)
    private List<MemberEntity> members;

    public void setPartyRatingAvg(){
        if(count!=0){
            partyRatingAvg = members.stream().mapToLong(m->m.getRating()).sum()/count;
        }else{
            partyRatingAvg=0L;
        }
    }
}