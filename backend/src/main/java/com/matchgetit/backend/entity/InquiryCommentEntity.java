package com.matchgetit.backend.entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private InquiryEntity inquiry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private InquiryCommentEntity parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<InquiryCommentEntity> subComments;

    private String content;
}
