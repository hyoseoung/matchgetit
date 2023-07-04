package com.matchgetit.backend.dto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StadiumDTO {
        private Long stdId;
        private String stdName;
        private String mngId;
        private String stdLink;
        private String xCor;
        private String yCor;
        private String stdAddress;
        private String stdStart;
        private String stdEnd;
        private String stdPn;
        private String stdImgUrl;
        private String stdMng;
        private String stdDetails;
}
