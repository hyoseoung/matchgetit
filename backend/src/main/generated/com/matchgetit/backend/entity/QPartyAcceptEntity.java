package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPartyAcceptEntity is a Querydsl query type for PartyAcceptEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPartyAcceptEntity extends EntityPathBase<PartyAcceptEntity> {

    private static final long serialVersionUID = 255906853L;

    public static final QPartyAcceptEntity partyAcceptEntity = new QPartyAcceptEntity("partyAcceptEntity");

    public final EnumPath<com.matchgetit.backend.constant.AcceptType> agreement = createEnum("agreement", com.matchgetit.backend.constant.AcceptType.class);

    public final DateTimePath<java.util.Date> overTime = createDateTime("overTime", java.util.Date.class);

    public final NumberPath<Integer> partyAcceptId = createNumber("partyAcceptId", Integer.class);

    public final NumberPath<Integer> partyLeaderId = createNumber("partyLeaderId", Integer.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public final StringPath userName = createString("userName");

    public QPartyAcceptEntity(String variable) {
        super(PartyAcceptEntity.class, forVariable(variable));
    }

    public QPartyAcceptEntity(Path<? extends PartyAcceptEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPartyAcceptEntity(PathMetadata metadata) {
        super(PartyAcceptEntity.class, metadata);
    }

}

