package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberEntity is a Querydsl query type for MemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberEntity extends EntityPathBase<MemberEntity> {

    private static final long serialVersionUID = -501386359L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberEntity memberEntity = new QMemberEntity("memberEntity");

    public final StringPath accountState = createString("accountState");

    public final EnumPath<com.matchgetit.backend.constant.AccountType> accountType = createEnum("accountType", com.matchgetit.backend.constant.AccountType.class);

    public final StringPath bankId = createString("bankId");

    public final DateTimePath<java.util.Date> banPeriod = createDateTime("banPeriod", java.util.Date.class);

    public final DatePath<java.util.Date> bDay = createDate("bDay", java.util.Date.class);

    public final StringPath chargeBank = createString("chargeBank");

    public final StringPath chargeId = createString("chargeId");

    public final StringPath email = createString("email");

    public final EnumPath<com.matchgetit.backend.constant.Gender> gender = createEnum("gender", com.matchgetit.backend.constant.Gender.class);

    public final DateTimePath<java.util.Date> lastConnectionDate = createDateTime("lastConnectionDate", java.util.Date.class);

    public final EnumPath<com.matchgetit.backend.constant.LogInType> loginType = createEnum("loginType", com.matchgetit.backend.constant.LogInType.class);

    public final StringPath lose = createString("lose");

    public final StringPath name = createString("name");

    public final StringPath nameEdit = createString("nameEdit");

    public final StringPath ownedCrd = createString("ownedCrd");

    public final NumberPath<Integer> ownedPoint = createNumber("ownedPoint", Integer.class);

    public final QPartyEntity partyEntity;

    public final StringPath pn = createString("pn");

    public final EnumPath<com.matchgetit.backend.constant.Proficiency> prfcn = createEnum("prfcn", com.matchgetit.backend.constant.Proficiency.class);

    public final StringPath pw = createString("pw");

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final StringPath recommendCount = createString("recommendCount");

    public final DatePath<java.util.Date> regDate = createDate("regDate", java.util.Date.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public final StringPath win = createString("win");

    public QMemberEntity(String variable) {
        this(MemberEntity.class, forVariable(variable), INITS);
    }

    public QMemberEntity(Path<? extends MemberEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberEntity(PathMetadata metadata, PathInits inits) {
        this(MemberEntity.class, metadata, inits);
    }

    public QMemberEntity(Class<? extends MemberEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.partyEntity = inits.isInitialized("partyEntity") ? new QPartyEntity(forProperty("partyEntity")) : null;
    }

}

