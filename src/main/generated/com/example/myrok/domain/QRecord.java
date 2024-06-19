package com.example.myrok.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecord is a Querydsl query type for Record
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecord extends EntityPathBase<Record> {

    private static final long serialVersionUID = 1041067806L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecord record = new QRecord("record");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DatePath<java.time.LocalDate> createdDate = _super.createdDate;

    public final BooleanPath deleted = createBoolean("deleted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DatePath<java.time.LocalDate> lastModifiedDate = _super.lastModifiedDate;

    public final ListPath<MemberRecord, QMemberRecord> memberRecordList = this.<MemberRecord, QMemberRecord>createList("memberRecordList", MemberRecord.class, QMemberRecord.class, PathInits.DIRECT2);

    public final QProject project;

    public final StringPath recordContent = createString("recordContent");

    public final StringPath recordContentSummary = createString("recordContentSummary");

    public final DatePath<java.time.LocalDate> recordDate = createDate("recordDate", java.time.LocalDate.class);

    public final StringPath recordName = createString("recordName");

    public final ListPath<RecordTag, QRecordTag> recordTagList = this.<RecordTag, QRecordTag>createList("recordTagList", RecordTag.class, QRecordTag.class, PathInits.DIRECT2);

    public final NumberPath<Long> recordWriterId = createNumber("recordWriterId", Long.class);

    public QRecord(String variable) {
        this(Record.class, forVariable(variable), INITS);
    }

    public QRecord(Path<? extends Record> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecord(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecord(PathMetadata metadata, PathInits inits) {
        this(Record.class, metadata, inits);
    }

    public QRecord(Class<? extends Record> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project")) : null;
    }

}

