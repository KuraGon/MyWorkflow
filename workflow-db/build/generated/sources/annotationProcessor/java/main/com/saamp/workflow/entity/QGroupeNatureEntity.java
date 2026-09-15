package com.saamp.workflow.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGroupeNatureEntity is a Querydsl query type for GroupeNatureEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupeNatureEntity extends EntityPathBase<GroupeNatureEntity> {

    private static final long serialVersionUID = -132754191L;

    public static final QGroupeNatureEntity groupeNatureEntity = new QGroupeNatureEntity("groupeNatureEntity");

    public final StringPath commentaire = createString("commentaire");

    public final StringPath id = createString("id");

    public final StringPath libelle = createString("libelle");

    public QGroupeNatureEntity(String variable) {
        super(GroupeNatureEntity.class, forVariable(variable));
    }

    public QGroupeNatureEntity(Path<? extends GroupeNatureEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGroupeNatureEntity(PathMetadata metadata) {
        super(GroupeNatureEntity.class, metadata);
    }

}

