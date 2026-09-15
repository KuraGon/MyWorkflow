package com.saamp.workflow.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPhoneUserEntity is a Querydsl query type for PhoneUserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPhoneUserEntity extends EntityPathBase<PhoneUserEntity> {

    private static final long serialVersionUID = -1415012709L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPhoneUserEntity phoneUserEntity = new QPhoneUserEntity("phoneUserEntity");

    public final StringPath company = createString("company");

    public final StringPath denomination = createString("denomination");

    public final StringPath firstname = createString("firstname");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath lastname = createString("lastname");

    public final NumberPath<Long> mysaampIdClient = createNumber("mysaampIdClient", Long.class);

    public final QPhoneContactEntity phoneOther;

    public final QPhoneContactEntity phonePerso;

    public final QPhoneContactEntity phonePro;

    public final QPhoneContactEntity phoneTelavox;

    public QPhoneUserEntity(String variable) {
        this(PhoneUserEntity.class, forVariable(variable), INITS);
    }

    public QPhoneUserEntity(Path<? extends PhoneUserEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhoneUserEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhoneUserEntity(PathMetadata metadata, PathInits inits) {
        this(PhoneUserEntity.class, metadata, inits);
    }

    public QPhoneUserEntity(Class<? extends PhoneUserEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.phoneOther = inits.isInitialized("phoneOther") ? new QPhoneContactEntity(forProperty("phoneOther")) : null;
        this.phonePerso = inits.isInitialized("phonePerso") ? new QPhoneContactEntity(forProperty("phonePerso")) : null;
        this.phonePro = inits.isInitialized("phonePro") ? new QPhoneContactEntity(forProperty("phonePro")) : null;
        this.phoneTelavox = inits.isInitialized("phoneTelavox") ? new QPhoneContactEntity(forProperty("phoneTelavox")) : null;
    }

}

