package com.matchgetit.backend.service;

import com.matchgetit.backend.dto.InquiryDTO;
import com.matchgetit.backend.entity.InquiryEntity;
import com.matchgetit.backend.repository.InquiryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryService {
    private final InquiryRepository inquiryRepository;

    public void createInquiries() {
        for (int i=1; i<=5; i++) {
            InquiryEntity inquiry = new InquiryEntity();
            inquiry.setTitle("테스트 문의"+i);
            inquiry.setContent("테스트 문의 내용"+i);
            inquiry.setCategory("테스트");
            inquiry.setState("처리 중");
            inquiryRepository.save(inquiry);
        }
    }

    @Transactional(readOnly = true)
    public List<InquiryDTO> getInquiryList() {
        List<InquiryEntity> inquiryList = inquiryRepository.findAll();
        List<InquiryDTO> inquiryDTOList = new ArrayList<>();

        for (InquiryEntity inquiry: inquiryList) {
            inquiryDTOList.add(InquiryDTO.of(inquiry));
        }
        return inquiryDTOList;
    }

    @Transactional(readOnly = true)
    public InquiryDTO getInquiry(Long id) {
        InquiryEntity inquiry = inquiryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return InquiryDTO.of(inquiry);
    }
}
