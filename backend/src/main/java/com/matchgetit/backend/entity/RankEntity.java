package com.matchgetit.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rank_brd")
@Data
public class RankEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RANK_ID")
    private String rankId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "GROUP_ID")
    private String groupId;

    @Column(name = "NAME")
    private String name;

    //승점
    @Column(name = "RATING")
    private Integer rating;

    @Column(name = "WIN")
    private String win;

    @Column(name = "LOSE")
    private String lose;

    @Column(name = "VIC_RATING")
    private String vicRating;

    private String rank;

}
