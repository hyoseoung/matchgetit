package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPaymentRecordEntity is a Querydsl query type for PaymentRecordEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaymentRecordEntity extends EntityPathBase<PaymentRecordEntity> {

    private static final long serialVersionUID = -480501042L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPaymentRecordEntity paymentRecordEntity = new QPaymentRecordEntity("paymentRecordEntity");

    public final DateTimePath<java.time.LocalDateTime> cancelDate = createDateTime("cancelDate", java.time.LocalDateTime.class);

    public final StringPath gameNumber = createString("gameNumber");

    public final QMemberEntity member;

    public final NumberPath<Long> paymentNumber = createNumber("paymentNumber", Long.class);

    public final DateTimePath<java.time.LocalDateTime> transactionDate = createDateTime("transactionDate", java.time.LocalDateTime.class);

    public final EnumPath<com.matchgetit.backend.constant.PaymentStatus> transactionStatus = createEnum("transactionStatus", com.matchgetit.backend.constant.PaymentStatus.class);

    public QPaymentRecordEntity(String variable) {
        this(PaymentRecordEntity.class, forVariable(variable), INITS);
    }

    public QPaymentRecordEntity(Path<? extends PaymentRecordEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPaymentRecordEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPaymentRecordEntity(PathMetadata metadata, PathInits inits) {
        this(PaymentRecordEntity.class, metadata, inits);
    }

    public QPaymentRecordEntity(Class<? extends PaymentRecordEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMemberEntity(forProperty("member"), inits.get("member")) : null;
    }

}

