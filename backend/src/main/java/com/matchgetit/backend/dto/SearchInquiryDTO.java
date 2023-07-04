package com.matchgetit.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchInquiryDTO {
    private String state;
    private String category;
    private String searchType;
    private String searchValue;
    private String regDateStart;
    private String regDateEnd;
    private Integer pageSize;
}
