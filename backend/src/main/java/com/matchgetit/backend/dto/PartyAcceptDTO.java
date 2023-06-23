package com.matchgetit.backend.dto;

import com.matchgetit.backend.constant.AcceptType;
import lombok.*;

import java.util.Date;

@Data
@Setter
public class PartyAcceptDTO {
        private int partyAcceptId;
        private int partyLeaderId;
        private AcceptType agreement;
        private Date overTime;
        private int userId;
        private String userName;
}
