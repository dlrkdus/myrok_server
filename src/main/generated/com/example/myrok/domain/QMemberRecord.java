package com.example.myrok.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberRecord is a Querydsl query type for MemberRecord
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberRecord extends EntityPathBase<MemberRecord> {

    private static final long serialVersionUID = -742851432L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberRecord memberRecord = new QMemberRecord("memberRecord");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DatePath<java.time.LocalDate> createdDate = _super.createdDate;

    public final BooleanPath deleted = createBoolean("deleted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DatePath<java.time.LocalDate> lastModifiedDate = _super.lastModifiedDate;

    public final QMember member;

    public final QRecord record;

    public final EnumPath<com.example.myrok.type.Role> role = createEnum("role", com.example.myrok.type.Role.class);

    public QMemberRecord(String variable) {
        this(MemberRecord.class, forVariable(variable), INITS);
    }

    public QMemberRecord(Path<? extends MemberRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberRecord(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberRecord(PathMetadata metadata, PathInits inits) {
        this(MemberRecord.class, metadata, inits);
    }

    public QMemberRecord(Class<? extends MemberRecord> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.record = inits.isInitialized("record") ? new QRecord(forProperty("record"), inits.get("record")) : null;
    }

}

