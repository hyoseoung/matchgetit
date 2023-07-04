package com.matchgetit.backend.dto;

import com.matchgetit.backend.entity.InquiryCommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
public class InquiryCommentDTO {
    private Long id;
    private String content;
    private String writedBy;
    private String regDate;
    private boolean isEdited;

//    private Long parentCommentId;
//    private String parentCommentWriter;
    private InquiryCommentDTO parent;

    private List<InquiryCommentDTO> subCommentList = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();


    public static InquiryCommentDTO of(InquiryCommentEntity comment) {
        modelMapper.typeMap(InquiryCommentEntity.class, InquiryCommentDTO.class)
                .addMappings(mapping -> {
                    mapping.map(InquiryCommentEntity::getCreatedBy, InquiryCommentDTO::setWritedBy);
                    mapping.map(InquiryCommentEntity::getRegTime, InquiryCommentDTO::setRegDate);
                });
        return modelMapper.map(comment, InquiryCommentDTO.class);
    }

    public void setRegDate(LocalDateTime regTime) {
        this.regDate = regTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));  //DateTimeFormatter.ISO_LOCAL_DATE
    }


    public InquiryCommentEntity toEntity() {
        modelMapper.typeMap(InquiryCommentDTO.class, InquiryCommentEntity.class)
                .addMappings(mapping -> {
                    mapping.map(InquiryCommentDTO::getWritedBy, InquiryCommentEntity::setCreatedBy);
                    mapping.map(InquiryCommentDTO::getRegDate, InquiryCommentEntity::setRegTime);
                });
        return modelMapper.map(this, InquiryCommentEntity.class);
    }
}
