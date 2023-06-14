package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPaymentRecord is a Querydsl query type for PaymentRecord
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaymentRecord extends EntityPathBase<PaymentRecord> {

    private static final long serialVersionUID = 1851131659L;

    public static final QPaymentRecord paymentRecord = new QPaymentRecord("paymentRecord");

    public final DateTimePath<java.time.LocalDateTime> cancelDate = createDateTime("cancelDate", java.time.LocalDateTime.class);

    public final StringPath gameNumber = createString("gameNumber");

    public final NumberPath<Long> paymentNumber = createNumber("paymentNumber", Long.class);

    public final DateTimePath<java.time.LocalDateTime> transactionDate = createDateTime("transactionDate", java.time.LocalDateTime.class);

    public final EnumPath<com.matchgetit.backend.constant.PaymentStatus> transactionStatus = createEnum("transactionStatus", com.matchgetit.backend.constant.PaymentStatus.class);

    public final StringPath userName = createString("userName");

    public final StringPath userNumber = createString("userNumber");

    public QPaymentRecord(String variable) {
        super(PaymentRecord.class, forVariable(variable));
    }

    public QPaymentRecord(Path<? extends PaymentRecord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPaymentRecord(PathMetadata metadata) {
        super(PaymentRecord.class, metadata);
    }

}

