package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDashboardDataEntity is a Querydsl query type for DashboardDataEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDashboardDataEntity extends EntityPathBase<DashboardDataEntity> {

    private static final long serialVersionUID = -170266635L;

    public static final QDashboardDataEntity dashboardDataEntity = new QDashboardDataEntity("dashboardDataEntity");

    public final NumberPath<Integer> canceledMatch = createNumber("canceledMatch", Integer.class);

    public final NumberPath<Integer> canceledMembership = createNumber("canceledMembership", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QDashboardDataEntity(String variable) {
        super(DashboardDataEntity.class, forVariable(variable));
    }

    public QDashboardDataEntity(Path<? extends DashboardDataEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDashboardDataEntity(PathMetadata metadata) {
        super(DashboardDataEntity.class, metadata);
    }

}

