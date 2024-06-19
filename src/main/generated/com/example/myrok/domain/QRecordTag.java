package com.example.myrok.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecordTag is a Querydsl query type for RecordTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecordTag extends EntityPathBase<RecordTag> {

    private static final long serialVersionUID = 492247964L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecordTag recordTag = new QRecordTag("recordTag");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DatePath<java.time.LocalDate> createdDate = _super.createdDate;

    public final BooleanPath deleted = createBoolean("deleted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DatePath<java.time.LocalDate> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Long> projectId = createNumber("projectId", Long.class);

    public final QRecord record;

    public final StringPath tagName = createString("tagName");

    public QRecordTag(String variable) {
        this(RecordTag.class, forVariable(variable), INITS);
    }

    public QRecordTag(Path<? extends RecordTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecordTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecordTag(PathMetadata metadata, PathInits inits) {
        this(RecordTag.class, metadata, inits);
    }

    public QRecordTag(Class<? extends RecordTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.record = inits.isInitialized("record") ? new QRecord(forProperty("record"), inits.get("record")) : null;
    }

}

