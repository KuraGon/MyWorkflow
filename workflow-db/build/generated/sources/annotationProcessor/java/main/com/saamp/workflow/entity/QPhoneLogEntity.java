package com.saamp.workflow.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPhoneLogEntity is a Querydsl query type for PhoneLogEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPhoneLogEntity extends EntityPathBase<PhoneLogEntity> {

    private static final long serialVersionUID = -1863044198L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPhoneLogEntity phoneLogEntity = new QPhoneLogEntity("phoneLogEntity");

    public final QPhoneContactEntity callerContact;

    public final StringPath callType = createString("callType");

    public final NumberPath<java.math.BigDecimal> costHt = createNumber("costHt", java.math.BigDecimal.class);

    public final QPhoneContactEntity destinationContact;

    public final StringPath direction = createString("direction");

    public final NumberPath<Integer> durationSec = createNumber("durationSec", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> eventDate = createDateTime("eventDate", java.time.LocalDateTime.class);

    public final QPhoneContactEntity forfaitContact;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final NumberPath<java.math.BigDecimal> quantityOctets = createNumber("quantityOctets", java.math.BigDecimal.class);

    public final StringPath type = createString("type");

    public final StringPath zoneClient = createString("zoneClient");

    public QPhoneLogEntity(String variable) {
        this(PhoneLogEntity.class, forVariable(variable), INITS);
    }

    public QPhoneLogEntity(Path<? extends PhoneLogEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhoneLogEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhoneLogEntity(PathMetadata metadata, PathInits inits) {
        this(PhoneLogEntity.class, metadata, inits);
    }

    public QPhoneLogEntity(Class<? extends PhoneLogEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.callerContact = inits.isInitialized("callerContact") ? new QPhoneContactEntity(forProperty("callerContact")) : null;
        this.destinationContact = inits.isInitialized("destinationContact") ? new QPhoneContactEntity(forProperty("destinationContact")) : null;
        this.forfaitContact = inits.isInitialized("forfaitContact") ? new QPhoneContactEntity(forProperty("forfaitContact")) : null;
    }

}

