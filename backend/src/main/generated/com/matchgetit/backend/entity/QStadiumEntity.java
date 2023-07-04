package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStadiumEntity is a Querydsl query type for StadiumEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStadiumEntity extends EntityPathBase<StadiumEntity> {

    private static final long serialVersionUID = -531729580L;

    public static final QStadiumEntity stadiumEntity = new QStadiumEntity("stadiumEntity");

    public final StringPath mngId = createString("mngId");

    public final StringPath stdAddress = createString("stdAddress");

    public final StringPath stdDetails = createString("stdDetails");

    public final StringPath stdEnd = createString("stdEnd");

    public final NumberPath<Long> stdId = createNumber("stdId", Long.class);

    public final StringPath stdImgUrl = createString("stdImgUrl");

    public final StringPath stdLink = createString("stdLink");

    public final StringPath stdMng = createString("stdMng");

    public final StringPath stdName = createString("stdName");

    public final StringPath stdPn = createString("stdPn");

    public final StringPath stdStart = createString("stdStart");

    public final StringPath xCor = createString("xCor");

    public final StringPath yCor = createString("yCor");

    public QStadiumEntity(String variable) {
        super(StadiumEntity.class, forVariable(variable));
    }

    public QStadiumEntity(Path<? extends StadiumEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStadiumEntity(PathMetadata metadata) {
        super(StadiumEntity.class, metadata);
    }

}

