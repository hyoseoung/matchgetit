package com.matchgetit.backend.service;

import com.matchgetit.backend.dto.InquiryCommentDTO;
import com.matchgetit.backend.dto.InquiryDTO;
import com.matchgetit.backend.dto.SearchInquiryDTO;
import com.matchgetit.backend.entity.InquiryCommentEntity;
import com.matchgetit.backend.entity.InquiryEntity;
import com.matchgetit.backend.repository.InquiryCommentRepository;
import com.matchgetit.backend.repository.InquiryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryService {
    private final InquiryRepository inquiryRepository;
    private final InquiryCommentRepository commentRepository;

    public void createInquiries() {
        for (int i=1; i<=10; i++) {
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
    public Page<InquiryDTO> getPageableInquiryList(SearchInquiryDTO searchInquiryDTO, Pageable pageable) {
        return inquiryRepository.getInquiryListPageBy(searchInquiryDTO, pageable);
    }


    @Transactional(readOnly = true)
    public InquiryDTO getInquiry(Long inquiryId) {
        List<InquiryCommentEntity> commentList = commentRepository.findByInquiryIdOrderByIdAsc(inquiryId);
        List<InquiryCommentDTO> commentDTOList = new ArrayList<>();

        for (InquiryCommentEntity comment: commentList) {
            InquiryCommentDTO commentDTO = InquiryCommentDTO.of(comment);
            commentDTOList.add(commentDTO);
        }

        InquiryEntity inquiry = inquiryRepository.findById(inquiryId).orElseThrow(EntityNotFoundException::new);
        InquiryDTO inquiryDTO = InquiryDTO.of(inquiry);
        inquiryDTO.setCommentList(commentDTOList);
        return inquiryDTO;
    }

    public void deleteInquiry(Long inquiryId) {
        inquiryRepository.deleteById(inquiryId);
    }

    public void saveInquiry(InquiryDTO inquiryDTO) {
        InquiryEntity inquiry = inquiryDTO.toEntity();
        inquiryRepository.save(inquiry);
    }



    public void writeComment(InquiryCommentDTO commentDTO, Long inquiryId) {
        InquiryEntity inquiry = inquiryRepository.findById(inquiryId).orElseThrow(EntityNotFoundException::new);
        InquiryCommentEntity comment = commentDTO.toEntity();
        comment.setInquiry(inquiry);
        commentRepository.save(comment);
//        System.out.println(">>>>>>>>>>>>>>>>"+commentRepository.findAll().get(0));
    }

    public void updateComment(String content, Long commentId) {
        InquiryCommentEntity comment = commentRepository.findById(commentId).orElseThrow(EntityNotFoundException::new);
        comment.updateComment(content);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
