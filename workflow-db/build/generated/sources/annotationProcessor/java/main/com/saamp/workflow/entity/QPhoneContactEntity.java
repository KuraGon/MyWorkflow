package com.saamp.workflow.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPhoneContactEntity is a Querydsl query type for PhoneContactEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPhoneContactEntity extends EntityPathBase<PhoneContactEntity> {

    private static final long serialVersionUID = -551764490L;

    public static final QPhoneContactEntity phoneContactEntity = new QPhoneContactEntity("phoneContactEntity");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath phoneNumber = createString("phoneNumber");

    public QPhoneContactEntity(String variable) {
        super(PhoneContactEntity.class, forVariable(variable));
    }

    public QPhoneContactEntity(Path<? extends PhoneContactEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPhoneContactEntity(PathMetadata metadata) {
        super(PhoneContactEntity.class, metadata);
    }

}

