package com.matchgetit.backend.repository;

import com.matchgetit.backend.constant.AccountState;
import com.matchgetit.backend.dto.AdminPageSearchUserDTO;
import com.matchgetit.backend.dto.AdminPageUserDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.sql.Date;
import java.util.List;

import static com.matchgetit.backend.entity.QMemberEntity.memberEntity;

public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    private JPAQueryFactory queryFactory;

    public MemberRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<AdminPageUserDTO> getUserListPageBy(AdminPageSearchUserDTO searchUserDTO, Pageable pageable) {
        List<AdminPageUserDTO> content = queryFactory
                .select(Projections.bean(AdminPageUserDTO.class,
                        memberEntity.userId.as("id"),
                        memberEntity.name,
                        memberEntity.email,
                        memberEntity.pn.as("phoneNum"),
                        memberEntity.gender,
                        memberEntity.bDay.stringValue().as("birthday"),
                        memberEntity.rating,
                        memberEntity.regDate.as("formattedRegDate"),
                        memberEntity.lastConnectionDate.as("formattedLoginDate"),
//                        memberEntity.state,
                        memberEntity.accountState))
                .from(memberEntity)
                .where(
                        searchWithCondition(searchUserDTO.getSearchType(), searchUserDTO.getSearchValue()),
                        regDateBetween(searchUserDTO),
//                        stateLike(searchUserDTO.getAccountState())
                        searchState(searchUserDTO.getAccountState())
                )
                .orderBy(memberEntity.userId.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(Wildcard.count)
                .from(memberEntity)
                .where(
                        searchWithCondition(searchUserDTO.getSearchType(), searchUserDTO.getSearchValue()),
                        regDateBetween(searchUserDTO),
//                        stateLike(searchUserDTO.getAccountState())
                        searchState(searchUserDTO.getAccountState())
                )
                .fetchFirst();

        return new PageImpl<>(content, pageable, total);
    }


    private BooleanExpression searchWithCondition(String searchType, String searchValue) {
        if (searchValue == null || searchValue.isEmpty()) return null;
        else if (StringUtils.equals("userId", searchType)) {
            return memberEntity.userId.like("%"+searchValue+"%");
        }
        else if (StringUtils.equals("userName", searchType)) {
//            return memberEntity.name.like("%"+searchValue+"%");
            return memberEntity.name.contains(searchValue);
        }
        else if (StringUtils.equals("email", searchType)) {
            return memberEntity.email.like("%"+searchValue+"%");
        }
        else if (StringUtils.equals("phoneNum", searchType)) {
            return memberEntity.pn.like("%"+searchValue+"%");
        }
        return null;
    }

    private BooleanExpression regDateBetween(AdminPageSearchUserDTO searchUserDTO) {
        String regDateStart = searchUserDTO.getRegDateStart();
        String regDateEnd = searchUserDTO.getRegDateEnd();

        if (regDateStart == null || regDateEnd == null
                || regDateStart.isEmpty() || regDateEnd.isEmpty())
            return null;
        Date from = Date.valueOf(regDateStart);
        Date to = Date.valueOf(regDateEnd);
        return memberEntity.regDate.between(from, to);
    }

    private BooleanExpression searchState(AccountState accountState) {
        return accountState == null ? null : memberEntity.accountState.eq(accountState);
    }

}
