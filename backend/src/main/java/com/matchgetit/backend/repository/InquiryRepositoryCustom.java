package com.matchgetit.backend.repository;

import com.matchgetit.backend.dto.InquiryDTO;
import com.matchgetit.backend.dto.SearchInquiryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InquiryRepositoryCustom {
//    List<InquiryEntity> getInquiryList();
    Page<InquiryDTO> getInquiryListPageBy(SearchInquiryDTO searchInquiryDTO, Pageable pageable);
}
