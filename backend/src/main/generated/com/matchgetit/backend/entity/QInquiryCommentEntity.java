package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInquiryCommentEntity is a Querydsl query type for InquiryCommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInquiryCommentEntity extends EntityPathBase<InquiryCommentEntity> {

    private static final long serialVersionUID = -1893989113L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInquiryCommentEntity inquiryCommentEntity = new QInquiryCommentEntity("inquiryCommentEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QInquiryEntity inquiry;

    public final BooleanPath isEdited = createBoolean("isEdited");

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final QInquiryCommentEntity parent;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final ListPath<InquiryCommentEntity, QInquiryCommentEntity> subComments = this.<InquiryCommentEntity, QInquiryCommentEntity>createList("subComments", InquiryCommentEntity.class, QInquiryCommentEntity.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QInquiryCommentEntity(String variable) {
        this(InquiryCommentEntity.class, forVariable(variable), INITS);
    }

    public QInquiryCommentEntity(Path<? extends InquiryCommentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInquiryCommentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInquiryCommentEntity(PathMetadata metadata, PathInits inits) {
        this(InquiryCommentEntity.class, metadata, inits);
    }

    public QInquiryCommentEntity(Class<? extends InquiryCommentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.inquiry = inits.isInitialized("inquiry") ? new QInquiryEntity(forProperty("inquiry")) : null;
        this.parent = inits.isInitialized("parent") ? new QInquiryCommentEntity(forProperty("parent"), inits.get("parent")) : null;
    }

}

