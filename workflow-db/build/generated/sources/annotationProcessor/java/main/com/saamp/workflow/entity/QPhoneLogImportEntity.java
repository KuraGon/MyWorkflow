package com.saamp.workflow.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPhoneLogImportEntity is a Querydsl query type for PhoneLogImportEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPhoneLogImportEntity extends EntityPathBase<PhoneLogImportEntity> {

    private static final long serialVersionUID = -1873174945L;

    public static final QPhoneLogImportEntity phoneLogImportEntity = new QPhoneLogImportEntity("phoneLogImportEntity");

    public final StringPath checksum = createString("checksum");

    public final StringPath fileName = createString("fileName");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final DateTimePath<java.time.LocalDateTime> importedAt = createDateTime("importedAt", java.time.LocalDateTime.class);

    public final StringPath message = createString("message");

    public final NumberPath<Long> sizeBytes = createNumber("sizeBytes", Long.class);

    public final StringPath status = createString("status");

    public QPhoneLogImportEntity(String variable) {
        super(PhoneLogImportEntity.class, forVariable(variable));
    }

    public QPhoneLogImportEntity(Path<? extends PhoneLogImportEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPhoneLogImportEntity(PathMetadata metadata) {
        super(PhoneLogImportEntity.class, metadata);
    }

}

