package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QManagerEntity is a Querydsl query type for ManagerEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QManagerEntity extends EntityPathBase<ManagerEntity> {

    private static final long serialVersionUID = -877575420L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QManagerEntity managerEntity = new QManagerEntity("managerEntity");

    public final EnumPath<com.matchgetit.backend.constant.EmploymentStatus> employmentStatus = createEnum("employmentStatus", com.matchgetit.backend.constant.EmploymentStatus.class);

    public final DatePath<java.time.LocalDate> leaveEndDate = createDate("leaveEndDate", java.time.LocalDate.class);

    public final StringPath leaveReason = createString("leaveReason");

    public final DatePath<java.time.LocalDate> leaveStartDate = createDate("leaveStartDate", java.time.LocalDate.class);

    public final NumberPath<Long> managerId = createNumber("managerId", Long.class);

    public final StringPath managerImage = createString("managerImage");

    public final EnumPath<com.matchgetit.backend.constant.MatchType> matchType = createEnum("matchType", com.matchgetit.backend.constant.MatchType.class);

    public final NumberPath<Long> partyId = createNumber("partyId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> registrationDate = createDateTime("registrationDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> stadiumId = createNumber("stadiumId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> startTime = createDateTime("startTime", java.time.LocalDateTime.class);

    public final StringPath team = createString("team");

    public final QMemberEntity user;

    public QManagerEntity(String variable) {
        this(ManagerEntity.class, forVariable(variable), INITS);
    }

    public QManagerEntity(Path<? extends ManagerEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QManagerEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QManagerEntity(PathMetadata metadata, PathInits inits) {
        this(ManagerEntity.class, metadata, inits);
    }

    public QManagerEntity(Class<? extends ManagerEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QMemberEntity(forProperty("user"), inits.get("user")) : null;
    }

}

