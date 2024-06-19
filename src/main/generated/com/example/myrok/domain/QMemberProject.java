package com.example.myrok.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberProject is a Querydsl query type for MemberProject
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberProject extends EntityPathBase<MemberProject> {

    private static final long serialVersionUID = 1349501874L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberProject memberProject = new QMemberProject("memberProject");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DatePath<java.time.LocalDate> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DatePath<java.time.LocalDate> lastModifiedDate = _super.lastModifiedDate;

    public final QMember member;

    public final EnumPath<com.example.myrok.type.MemberProjectType> memberProjectType = createEnum("memberProjectType", com.example.myrok.type.MemberProjectType.class);

    public final QProject project;

    public QMemberProject(String variable) {
        this(MemberProject.class, forVariable(variable), INITS);
    }

    public QMemberProject(Path<? extends MemberProject> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberProject(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberProject(PathMetadata metadata, PathInits inits) {
        this(MemberProject.class, metadata, inits);
    }

    public QMemberProject(Class<? extends MemberProject> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project")) : null;
    }

}

