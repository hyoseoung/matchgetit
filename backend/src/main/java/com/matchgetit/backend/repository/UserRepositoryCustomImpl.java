package com.matchgetit.backend.repository;


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

import static com.matchgetit.backend.entity.QUser.user;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private JPAQueryFactory queryFactory;

    public UserRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

//    @Override
//    public Page<User> getUserListPage2(Pageable pageable) {
//        List<User> content = queryFactory
//                .selectFrom(user)
//                .fetch();
//        return null;
//    }

    @Override
    public Page<AdminPageUserDTO> getUserListPageBy(AdminPageSearchUserDTO searchUserDTO, Pageable pageable) {
        List<AdminPageUserDTO> content = queryFactory
                .select(Projections.bean(AdminPageUserDTO.class,
                        user.id,
                        user.name,
                        user.email,
                        user.phoneNum,
                        user.gender,
                        user.birthday.stringValue().as("birthday"),
                        user.score.as("score"),
                        user.regDate.stringValue().as("regDate"),
                        user.loginDate.stringValue().as("loginDate"),
                        user.state))
                .from(user)
                .where(
                        searchWithCondition(searchUserDTO.getSearchType(), searchUserDTO.getSearchValue()),
                        regDateBetween(searchUserDTO),
                        stateLike(searchUserDTO.getAccountState())
                )
                .orderBy(user.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(Wildcard.count)
                .from(user)
                .where(
                        searchWithCondition(searchUserDTO.getSearchType(), searchUserDTO.getSearchValue()),
                        regDateBetween(searchUserDTO),
                        stateLike(searchUserDTO.getAccountState())
                )
                .fetchFirst();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression searchWithCondition(String searchType, String searchValue) {
        if (searchValue == null || searchValue.isEmpty()) return null;
        else if (StringUtils.equals("userId", searchType)) {
            return user.id.like("%"+searchValue+"%");
        }
        else if (StringUtils.equals("userName", searchType)) {
//            return user.name.like("%"+searchValue+"%");
            return user.name.contains(searchValue);
        }
        else if (StringUtils.equals("email", searchType)) {
            return user.email.like("%"+searchValue+"%");
        }
        else if (StringUtils.equals("phoneNum", searchType)) {
            return user.phoneNum.like("%"+searchValue+"%");
        }
        return null;
    }

    private BooleanExpression regDateBetween(AdminPageSearchUserDTO searchUserDTO) {
        if (searchUserDTO.getRegDateStart() == null || searchUserDTO.getRegDateEnd() == null
                || searchUserDTO.getRegDateStart().isEmpty() || searchUserDTO.getRegDateEnd().isEmpty())
            return null;
        Date from = Date.valueOf(searchUserDTO.getRegDateStart());
        Date to = Date.valueOf(searchUserDTO.getRegDateEnd());
        return user.regDate.between(from, to);
    }

    private BooleanExpression stateLike(String state) {
        if (state == null || state.isEmpty()) {
            return null;
        }
        else {
            return user.state.like("%"+state+"%");
        }
    }

}
