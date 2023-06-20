package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMatchUserEntity is a Querydsl query type for MatchUserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMatchUserEntity extends EntityPathBase<MatchUserEntity> {

    private static final long serialVersionUID = -1354424505L;

    public static final QMatchUserEntity matchUserEntity = new QMatchUserEntity("matchUserEntity");

    public final EnumPath<com.matchgetit.backend.constant.AccountType> accountType = createEnum("accountType", com.matchgetit.backend.constant.AccountType.class);

    public final StringPath email = createString("email");

    public final EnumPath<com.matchgetit.backend.constant.Gender> gender = createEnum("gender", com.matchgetit.backend.constant.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath pn = createString("pn");

    public final EnumPath<com.matchgetit.backend.constant.Proficiency> proficiency = createEnum("proficiency", com.matchgetit.backend.constant.Proficiency.class);

    public QMatchUserEntity(String variable) {
        super(MatchUserEntity.class, forVariable(variable));
    }

    public QMatchUserEntity(Path<? extends MatchUserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMatchUserEntity(PathMetadata metadata) {
        super(MatchUserEntity.class, metadata);
    }

}

