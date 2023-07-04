package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QManagerSupportRecordEntity is a Querydsl query type for ManagerSupportRecordEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QManagerSupportRecordEntity extends EntityPathBase<ManagerSupportRecordEntity> {

    private static final long serialVersionUID = 457215874L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QManagerSupportRecordEntity managerSupportRecordEntity = new QManagerSupportRecordEntity("managerSupportRecordEntity");

    public final StringPath activityZone = createString("activityZone");

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final QManagerEntity manager;

    public final QMemberEntity managerUser;

    public final DateTimePath<java.util.Date> submissionDate = createDateTime("submissionDate", java.util.Date.class);

    public QManagerSupportRecordEntity(String variable) {
        this(ManagerSupportRecordEntity.class, forVariable(variable), INITS);
    }

    public QManagerSupportRecordEntity(Path<? extends ManagerSupportRecordEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QManagerSupportRecordEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QManagerSupportRecordEntity(PathMetadata metadata, PathInits inits) {
        this(ManagerSupportRecordEntity.class, metadata, inits);
    }

    public QManagerSupportRecordEntity(Class<? extends ManagerSupportRecordEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.manager = inits.isInitialized("manager") ? new QManagerEntity(forProperty("manager"), inits.get("manager")) : null;
        this.managerUser = inits.isInitialized("managerUser") ? new QMemberEntity(forProperty("managerUser"), inits.get("managerUser")) : null;
    }

}

