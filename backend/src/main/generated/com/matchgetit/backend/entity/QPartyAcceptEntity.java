package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPartyAcceptEntity is a Querydsl query type for PartyAcceptEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPartyAcceptEntity extends EntityPathBase<PartyAcceptEntity> {

    private static final long serialVersionUID = 255906853L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPartyAcceptEntity partyAcceptEntity = new QPartyAcceptEntity("partyAcceptEntity");

    public final EnumPath<com.matchgetit.backend.constant.AcceptType> agreement = createEnum("agreement", com.matchgetit.backend.constant.AcceptType.class);

    public final DateTimePath<java.util.Date> overTime = createDateTime("overTime", java.util.Date.class);

    public final NumberPath<Long> partyAcceptId = createNumber("partyAcceptId", Long.class);

    public final QMemberEntity partyLeader;

    public final QMemberEntity user;

    public QPartyAcceptEntity(String variable) {
        this(PartyAcceptEntity.class, forVariable(variable), INITS);
    }

    public QPartyAcceptEntity(Path<? extends PartyAcceptEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPartyAcceptEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPartyAcceptEntity(PathMetadata metadata, PathInits inits) {
        this(PartyAcceptEntity.class, metadata, inits);
    }

    public QPartyAcceptEntity(Class<? extends PartyAcceptEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.partyLeader = inits.isInitialized("partyLeader") ? new QMemberEntity(forProperty("partyLeader"), inits.get("partyLeader")) : null;
        this.user = inits.isInitialized("user") ? new QMemberEntity(forProperty("user"), inits.get("user")) : null;
    }

}

