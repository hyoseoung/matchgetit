package com.matchgetit.backend.repository;

import com.matchgetit.backend.entity.InquiryCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryCommentRepository extends JpaRepository<InquiryCommentEntity, Long> {
}
