package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMatchWaitEntity is a Querydsl query type for MatchWaitEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMatchWaitEntity extends EntityPathBase<MatchWaitEntity> {

    private static final long serialVersionUID = 878271857L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMatchWaitEntity matchWaitEntity = new QMatchWaitEntity("matchWaitEntity");

    public final NumberPath<Long> crd = createNumber("crd", Long.class);

    public final NumberPath<Integer> cycle = createNumber("cycle", Integer.class);

    public final NumberPath<Long> matchWaitId = createNumber("matchWaitId", Long.class);

    public final QMemberEntity member;

    public final QPartyEntity party;

    public final NumberPath<Long> point = createNumber("point", Long.class);

    public final DateTimePath<java.util.Date> searchEnd = createDateTime("searchEnd", java.util.Date.class);

    public final DateTimePath<java.util.Date> searchStart = createDateTime("searchStart", java.util.Date.class);

    public final QStadiumEntity stadium;

    public final StringPath team = createString("team");

    public QMatchWaitEntity(String variable) {
        this(MatchWaitEntity.class, forVariable(variable), INITS);
    }

    public QMatchWaitEntity(Path<? extends MatchWaitEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMatchWaitEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMatchWaitEntity(PathMetadata metadata, PathInits inits) {
        this(MatchWaitEntity.class, metadata, inits);
    }

    public QMatchWaitEntity(Class<? extends MatchWaitEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMemberEntity(forProperty("member"), inits.get("member")) : null;
        this.party = inits.isInitialized("party") ? new QPartyEntity(forProperty("party"), inits.get("party")) : null;
        this.stadium = inits.isInitialized("stadium") ? new QStadiumEntity(forProperty("stadium")) : null;
    }

}

