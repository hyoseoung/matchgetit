package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMatchEntity is a Querydsl query type for MatchEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMatchEntity extends EntityPathBase<MatchEntity> {

    private static final long serialVersionUID = 1386696732L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMatchEntity matchEntity = new QMatchEntity("matchEntity");

    public final EnumPath<com.matchgetit.backend.constant.AcceptType> accept = createEnum("accept", com.matchgetit.backend.constant.AcceptType.class);

    public final NumberPath<Long> crd = createNumber("crd", Long.class);

    public final NumberPath<Integer> cycle = createNumber("cycle", Integer.class);

    public final NumberPath<Long> matchId = createNumber("matchId", Long.class);

    public final QMemberEntity member;

    public final QPartyEntity party;

    public final NumberPath<Long> point = createNumber("point", Long.class);

    public final DateTimePath<java.util.Date> searchEnd = createDateTime("searchEnd", java.util.Date.class);

    public final DateTimePath<java.util.Date> searchStart = createDateTime("searchStart", java.util.Date.class);

    public final QStadiumEntity stadium;

    public QMatchEntity(String variable) {
        this(MatchEntity.class, forVariable(variable), INITS);
    }

    public QMatchEntity(Path<? extends MatchEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMatchEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMatchEntity(PathMetadata metadata, PathInits inits) {
        this(MatchEntity.class, metadata, inits);
    }

    public QMatchEntity(Class<? extends MatchEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMemberEntity(forProperty("member"), inits.get("member")) : null;
        this.party = inits.isInitialized("party") ? new QPartyEntity(forProperty("party"), inits.get("party")) : null;
        this.stadium = inits.isInitialized("stadium") ? new QStadiumEntity(forProperty("stadium")) : null;
    }

}

