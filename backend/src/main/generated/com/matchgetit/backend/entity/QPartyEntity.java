package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPartyEntity is a Querydsl query type for PartyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPartyEntity extends EntityPathBase<PartyEntity> {

    private static final long serialVersionUID = 1376416893L;

    public static final QPartyEntity partyEntity = new QPartyEntity("partyEntity");

    public final DateTimePath<java.util.Date> applicationDate = createDateTime("applicationDate", java.util.Date.class);

    public final StringPath applicationTime = createString("applicationTime");

    public final StringPath locationX = createString("locationX");

    public final StringPath locationY = createString("locationY");

    public final NumberPath<Integer> partyId = createNumber("partyId", Integer.class);

    public final NumberPath<Integer> partyLeader = createNumber("partyLeader", Integer.class);

    public final NumberPath<Integer> stadiumId = createNumber("stadiumId", Integer.class);

    public QPartyEntity(String variable) {
        super(PartyEntity.class, forVariable(variable));
    }

    public QPartyEntity(Path<? extends PartyEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPartyEntity(PathMetadata metadata) {
        super(PartyEntity.class, metadata);
    }

}

