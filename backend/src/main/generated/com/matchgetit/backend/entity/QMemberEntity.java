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

    public final EnumPath<com.matchgetit.backend.constant.AccountState> accountState = createEnum("accountState", com.matchgetit.backend.constant.AccountState.class);

    public final EnumPath<com.matchgetit.backend.constant.AccountType> accountType = createEnum("accountType", com.matchgetit.backend.constant.AccountType.class);

    public final DateTimePath<java.util.Date> banDateEnd = createDateTime("banDateEnd", java.util.Date.class);

    public final DateTimePath<java.util.Date> banDateStart = createDateTime("banDateStart", java.util.Date.class);

    public final StringPath bankId = createString("bankId");

    public final StringPath banReason = createString("banReason");

    public final DatePath<java.util.Date> bDay = createDate("bDay", java.util.Date.class);

    public final NumberPath<Long> chargeId = createNumber("chargeId", Long.class);

    public final StringPath email = createString("email");

    public final EnumPath<com.matchgetit.backend.constant.Gender> gender = createEnum("gender", com.matchgetit.backend.constant.Gender.class);

    public final DateTimePath<java.util.Date> lastConnectionDate = createDateTime("lastConnectionDate", java.util.Date.class);

    public final EnumPath<com.matchgetit.backend.constant.LogInType> loginType = createEnum("loginType", com.matchgetit.backend.constant.LogInType.class);

    public final NumberPath<Long> lose = createNumber("lose", Long.class);

    public final QManagerEntity managerEntity;

    public final QManagerSupportRecordEntity managerSupportRecordEntity;

    public final EnumPath<com.matchgetit.backend.constant.ManagerSupportStatus> managerSupportStatus = createEnum("managerSupportStatus", com.matchgetit.backend.constant.ManagerSupportStatus.class);

    public final StringPath name = createString("name");

    public final StringPath nameEdit = createString("nameEdit");

    public final NumberPath<Long> ownedCrd = createNumber("ownedCrd", Long.class);

    public final NumberPath<Long> ownedPoint = createNumber("ownedPoint", Long.class);

    public final QPartyEntity party;

    public final ListPath<PaymentRecordEntity, QPaymentRecordEntity> paymentRecordEntityList = this.<PaymentRecordEntity, QPaymentRecordEntity>createList("paymentRecordEntityList", PaymentRecordEntity.class, QPaymentRecordEntity.class, PathInits.DIRECT2);

    public final EnumPath<com.matchgetit.backend.constant.PayState> payState = createEnum("payState", com.matchgetit.backend.constant.PayState.class);

    public final StringPath pn = createString("pn");

    public final EnumPath<com.matchgetit.backend.constant.Proficiency> prfcn = createEnum("prfcn", com.matchgetit.backend.constant.Proficiency.class);

    public final StringPath pw = createString("pw");

    public final StringPath rank = createString("rank");

    public final NumberPath<Long> rating = createNumber("rating", Long.class);

    public final StringPath recommendCount = createString("recommendCount");

    public final DatePath<java.util.Date> regDate = createDate("regDate", java.util.Date.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final NumberPath<Long> win = createNumber("win", Long.class);

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
        this.managerEntity = inits.isInitialized("managerEntity") ? new QManagerEntity(forProperty("managerEntity"), inits.get("managerEntity")) : null;
        this.managerSupportRecordEntity = inits.isInitialized("managerSupportRecordEntity") ? new QManagerSupportRecordEntity(forProperty("managerSupportRecordEntity"), inits.get("managerSupportRecordEntity")) : null;
        this.party = inits.isInitialized("party") ? new QPartyEntity(forProperty("party"), inits.get("party")) : null;
    }

}

