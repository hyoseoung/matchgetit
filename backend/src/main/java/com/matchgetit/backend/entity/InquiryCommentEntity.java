package com.matchgetit.backend.entity;

import com.matchgetit.backend.dto.InquiryCommentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "reply")
@Getter @Setter
public class InquiryCommentEntity extends BaseEntity {
    @Id
    @Column(name = "reply_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private InquiryEntity inquiry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private InquiryCommentEntity parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<InquiryCommentEntity> subComments;

    private Boolean isEdited = false;


    public InquiryCommentEntity createComment(InquiryEntity inquiry, List<InquiryCommentEntity> subComments) {
        InquiryCommentEntity comment = new InquiryCommentEntity();
        comment.setInquiry(inquiry);
        for (InquiryCommentEntity subComment: subComments) {
            comment.addSubComment(subComment);
        }
        return comment;
    }

    public void addSubComment(InquiryCommentEntity subComment) {
        subComments.add(subComment);
        subComment.setParent(this);
    }


    public void updateComment(InquiryCommentDTO commentDTO) {
        this.content = commentDTO.getContent();
        this.isEdited = true;
    }

    public void updateComment(String content) {
        this.content = content;
        this.isEdited = true;
    }


    @Override
    public String toString() {
        return this.id+": "+content+" / "+inquiry.getTitle()+": "+inquiry.getContent();
    }
}
