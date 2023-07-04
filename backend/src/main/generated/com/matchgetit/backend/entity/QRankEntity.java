package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRankEntity is a Querydsl query type for RankEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRankEntity extends EntityPathBase<RankEntity> {

    private static final long serialVersionUID = -686593381L;

    public static final QRankEntity rankEntity = new QRankEntity("rankEntity");

    public final StringPath groupId = createString("groupId");

    public final StringPath lose = createString("lose");

    public final StringPath name = createString("name");

    public final StringPath rank = createString("rank");

    public final StringPath rankId = createString("rankId");

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final StringPath userId = createString("userId");

    public final StringPath vicRating = createString("vicRating");

    public final StringPath win = createString("win");

    public QRankEntity(String variable) {
        super(RankEntity.class, forVariable(variable));
    }

    public QRankEntity(Path<? extends RankEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRankEntity(PathMetadata metadata) {
        super(RankEntity.class, metadata);
    }

}

