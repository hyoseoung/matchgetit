package com.matchgetit.backend.dto;

import com.matchgetit.backend.entity.InquiryEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter @ToString
public class InquiryDTO {
    private Long id;
    private String title;
    private String content;
    private String category;
    private String state;
    private String writedBy;
    private String regDate;

    private static ModelMapper modelMapper = new ModelMapper();

    public static InquiryDTO of(InquiryEntity inquiry) {
        modelMapper.typeMap(InquiryEntity.class, InquiryDTO.class)
                .addMappings(mapping -> {
                    mapping.map(InquiryEntity::getCreatedBy, InquiryDTO::setWritedBy);
                    mapping.map(InquiryEntity::getRegTime, InquiryDTO::setRegDate);
                });
        return modelMapper.map(inquiry, InquiryDTO.class);
    }

    public void setRegDate(LocalDateTime regTime) {
        this.regDate = regTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));  //DateTimeFormatter.ISO_LOCAL_DATE
    }
}
