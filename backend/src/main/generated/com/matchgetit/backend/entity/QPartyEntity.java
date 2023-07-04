package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPartyEntity is a Querydsl query type for PartyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPartyEntity extends EntityPathBase<PartyEntity> {

    private static final long serialVersionUID = 1376416893L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPartyEntity partyEntity = new QPartyEntity("partyEntity");

    public final StringPath address = createString("address");

    public final DatePath<java.util.Date> applicationDate = createDate("applicationDate", java.util.Date.class);

    public final StringPath applicationTime = createString("applicationTime");

    public final NumberPath<Long> count = createNumber("count", Long.class);

    public final StringPath gameType = createString("gameType");

    public final StringPath locationX = createString("locationX");

    public final StringPath locationY = createString("locationY");

    public final ListPath<MemberEntity, QMemberEntity> members = this.<MemberEntity, QMemberEntity>createList("members", MemberEntity.class, QMemberEntity.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> partyApplicationDate = createDateTime("partyApplicationDate", java.util.Date.class);

    public final NumberPath<Long> partyId = createNumber("partyId", Long.class);

    public final QMemberEntity partyLeader;

    public final NumberPath<Long> partyRatingAvg = createNumber("partyRatingAvg", Long.class);

    public QPartyEntity(String variable) {
        this(PartyEntity.class, forVariable(variable), INITS);
    }

    public QPartyEntity(Path<? extends PartyEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPartyEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPartyEntity(PathMetadata metadata, PathInits inits) {
        this(PartyEntity.class, metadata, inits);
    }

    public QPartyEntity(Class<? extends PartyEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.partyLeader = inits.isInitialized("partyLeader") ? new QMemberEntity(forProperty("partyLeader"), inits.get("partyLeader")) : null;
    }

}

