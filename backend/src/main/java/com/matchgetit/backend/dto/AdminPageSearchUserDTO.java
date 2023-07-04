package com.matchgetit.backend.dto;

import com.matchgetit.backend.constant.AccountState;
import lombok.Getter;
import lombok.Setter;

//import java.sql.Date;

@Getter @Setter
public class AdminPageSearchUserDTO {
    private AccountState accountState;
    private String searchType;
    private String searchValue;
    private String regDateStart;
    private String regDateEnd;
    private Integer pageSize;
}
