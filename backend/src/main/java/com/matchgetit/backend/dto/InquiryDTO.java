package com.matchgetit.backend.dto;

import com.matchgetit.backend.entity.InquiryEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
public class InquiryDTO {
    private Long id;
    private String title;
    private String content;
    private String category;
    private String state;
    private String writedBy;
    private String regDate;
//    private LocalDateTime regTime;

    private List<InquiryCommentDTO> commentList = new ArrayList<>();


    private static ModelMapper modelMapper = new ModelMapper();

    public static InquiryDTO of(InquiryEntity inquiry) {
        modelMapper.typeMap(InquiryEntity.class, InquiryDTO.class)
                .addMappings(mapping -> {
                    mapping.map(InquiryEntity::getCreatedBy, InquiryDTO::setWritedBy);
//                    mapping.map(InquiryEntity::getRegTime, InquiryDTO::setRegTime);
                });
        return modelMapper.map(inquiry, InquiryDTO.class);
    }

    public InquiryEntity toEntity() {
        modelMapper.typeMap(InquiryDTO.class, InquiryEntity.class)
                .addMappings(mapping -> {
//                    mapping.map(InquiryDTO::getWritedBy, InquiryEntity::setCreatedBy);
                    mapping.skip(InquiryEntity::setId);
                    mapping.skip(InquiryEntity::setCreatedBy);
                    mapping.skip(InquiryEntity::setRegTime);
                });
        return modelMapper.map(this, InquiryEntity.class);
    }


    public void setRegTime(LocalDateTime regTime) {
        this.regDate = regTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));  //DateTimeFormatter.ISO_LOCAL_DATE
    }

//    public void setRegDate(String regDate) {
//        this.regDate = regDate;
//    }

    public LocalDateTime getRegTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(this.regDate, formatter);
    }
}
