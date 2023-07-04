package com.matchgetit.backend.repository;

import com.matchgetit.backend.dto.InquiryDTO;
import com.matchgetit.backend.dto.SearchInquiryDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.matchgetit.backend.entity.QInquiryEntity.inquiryEntity;

public class InquiryRepositoryCustomImpl implements InquiryRepositoryCustom {
    private JPAQueryFactory queryFactory;

    public InquiryRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<InquiryDTO> getInquiryListPageBy(SearchInquiryDTO searchInquiryDTO, Pageable pageable) {
        List<InquiryDTO> content = queryFactory
                .select(Projections.bean(InquiryDTO.class,
                        inquiryEntity.id,
                        inquiryEntity.title,
                        inquiryEntity.content,
                        inquiryEntity.category,
                        inquiryEntity.state,
                        inquiryEntity.createdBy.as("writedBy"),
                        inquiryEntity.regTime))
                .from(inquiryEntity)
                .where(
                        searchWithCondition(searchInquiryDTO.getSearchType(), searchInquiryDTO.getSearchValue()),
                        regDateBetween(searchInquiryDTO),
                        searchState(searchInquiryDTO.getState()),
                        searchCategory(searchInquiryDTO.getCategory())
                )
                .orderBy(inquiryEntity.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(Wildcard.count)
                .from(inquiryEntity)
                .where(
                        searchWithCondition(searchInquiryDTO.getSearchType(), searchInquiryDTO.getSearchValue()),
                        regDateBetween(searchInquiryDTO),
                        searchState(searchInquiryDTO.getState()),
                        searchCategory(searchInquiryDTO.getCategory())
                )
                .fetchFirst();

        return new PageImpl<>(content, pageable, total);
    }


    private BooleanExpression searchWithCondition(String searchType, String searchValue) {
        if (searchValue == null || searchValue.isEmpty()) return null;
        else if (StringUtils.equals("title", searchType)) {
            return inquiryEntity.title.contains(searchValue);
        }
        else if (StringUtils.equals("content", searchType)) {
            return inquiryEntity.content.contains(searchValue);
        }
        else if (StringUtils.equals("writedBy", searchType)) {
            return inquiryEntity.createdBy.contains(searchValue);
        }
        return null;
    }


    private BooleanExpression regDateBetween(SearchInquiryDTO searchInquiryDTO) {
        String regDateStart = searchInquiryDTO.getRegDateStart();
        String regDateEnd = searchInquiryDTO.getRegDateEnd();

        if (regDateStart == null || regDateEnd == null || regDateStart.isEmpty() || regDateEnd.isEmpty())
            return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime from = LocalDateTime.parse(regDateStart, formatter);
        LocalDateTime to = LocalDateTime.parse(regDateEnd, formatter);
        return inquiryEntity.regTime.between(from, to);
    }


    private BooleanExpression searchState(String state) {
        return state == null ? null : inquiryEntity.state.contains(state);
    }

    private BooleanExpression searchCategory(String category) {
        return category == null ? null : inquiryEntity.category.contains(category);
    }
}
