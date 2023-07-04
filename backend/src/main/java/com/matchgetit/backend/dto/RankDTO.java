package com.matchgetit.backend.dto;

import com.matchgetit.backend.entity.MemberEntity;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RankDTO {
    private Long userId;
    private String groupId;
    private String name;
    private Long rating;
    private Long win;
    private Long lose;
    private String vicRating;
    private int rank;

    public RankDTO(Long userId,String groupId, String name, Long rating, Long win , Long lose, String vicRating, int rank){
        this.userId = userId;
        this.groupId = groupId;
        this.name = name;
        this.rating = rating;
        this.win = win;
        this.lose = lose;
        this.vicRating = vicRating;
        this.rank = rank;
    }

}
