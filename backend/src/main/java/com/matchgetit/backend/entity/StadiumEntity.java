package com.matchgetit.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="STADIUM")
@Data
@Setter
public class StadiumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="STD_ID")
    private Long stdId;
    @Column(name="STD_NAME")
    private String stdName;
    @Column(name="MNG_ID")
    private String mngId;
    @Column(name="STD_LINK")
    private String stdLink;
    @Column(name="X_COR")
    private String xCor;
    @Column(name="Y_COR")
    private String yCor;
    @Column(name="STD_ST_ADR")
    private String stdAddress;
    @Column(name="STD_START")
    private String stdStart;
    @Column(name="STD_END")
    private String stdEnd;
    @Column(name="STD_PN")
    private String stdPn;
    @Column(name="STD_IMG_URL")
    private String stdImgUrl;
    @Column(name="STD_MNG")
    private String stdMng;
    @Column(name="STD_DETAILS")
    private String stdDetails;

}
