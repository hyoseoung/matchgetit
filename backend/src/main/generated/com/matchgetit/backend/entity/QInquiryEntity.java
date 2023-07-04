package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInquiryEntity is a Querydsl query type for InquiryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInquiryEntity extends EntityPathBase<InquiryEntity> {

    private static final long serialVersionUID = 727269790L;

    public static final QInquiryEntity inquiryEntity = new QInquiryEntity("inquiryEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath category = createString("category");

    public final StringPath content = createString("content");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final StringPath state = createString("state");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QInquiryEntity(String variable) {
        super(InquiryEntity.class, forVariable(variable));
    }

    public QInquiryEntity(Path<? extends InquiryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInquiryEntity(PathMetadata metadata) {
        super(InquiryEntity.class, metadata);
    }

}

