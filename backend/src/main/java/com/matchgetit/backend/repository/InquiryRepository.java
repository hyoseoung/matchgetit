package com.matchgetit.backend.repository;

import com.matchgetit.backend.entity.InquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<InquiryEntity, Long>, InquiryRepositoryCustom {
}
