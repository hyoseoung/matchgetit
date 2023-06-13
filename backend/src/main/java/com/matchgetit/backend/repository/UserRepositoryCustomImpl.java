package com.matchgetit.backend.repository;

import com.matchgetit.backend.dto.AdminPageUserDTO;
import com.matchgetit.backend.entity.QUser;
import com.matchgetit.backend.entity.User;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
    public Page<AdminPageUserDTO> getUserListPageBy(Pageable pageable) {
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
                .orderBy(user.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(Wildcard.count)
                .from(user)
                .fetchFirst();

        return new PageImpl<>(content, pageable, total);
    }

//    private BooleanExpression searchWithCondition() {
//
//    }
}
