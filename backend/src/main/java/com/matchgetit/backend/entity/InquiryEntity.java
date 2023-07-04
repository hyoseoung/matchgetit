package com.matchgetit.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "INQUIRY")
@Getter @Setter @ToString
public class InquiryEntity extends BaseEntity{
    @Id
    @Column(name = "inquiry_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String content;

    private String category;
    private String state;

//    @OneToMany(mappedBy = "inquiry", fetch = FetchType.LAZY)
//    private List<InquiryCommentEntity> comments = new ArrayList<>();

}
