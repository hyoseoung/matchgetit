package com.matchgetit.backend.repository;

import com.matchgetit.backend.entity.InquiryCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryCommentRepository extends JpaRepository<InquiryCommentEntity, Long> {
    List<InquiryCommentEntity> findByInquiryIdOrderByIdAsc(Long inquiryId);
}
