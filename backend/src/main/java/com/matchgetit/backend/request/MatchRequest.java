package com.matchgetit.backend.request;

import com.matchgetit.backend.constant.GameType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MatchRequest {
    private String partyLeader;
    private String address;
    private String x;
    private String y;
    private String selectedDate;
    private String selectedTime;
    private List<MemberIdRequest> party;
    private GameType gameType;
}
