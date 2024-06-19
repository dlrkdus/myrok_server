package com.example.myrok.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProject extends EntityPathBase<Project> {

    private static final long serialVersionUID = 816423404L;

    public static final QProject project = new QProject("project");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DatePath<java.time.LocalDate> createdDate = _super.createdDate;

    public final BooleanPath deleted = createBoolean("deleted");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath inviteCode = createString("inviteCode");

    //inherited
    public final DatePath<java.time.LocalDate> lastModifiedDate = _super.lastModifiedDate;

    public final ListPath<MemberProject, QMemberProject> memberProjects = this.<MemberProject, QMemberProject>createList("memberProjects", MemberProject.class, QMemberProject.class, PathInits.DIRECT2);

    public final StringPath projectName = createString("projectName");

    public final ListPath<Record, QRecord> record = this.<Record, QRecord>createList("record", Record.class, QRecord.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public QProject(String variable) {
        super(Project.class, forVariable(variable));
    }

    public QProject(Path<? extends Project> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProject(PathMetadata metadata) {
        super(Project.class, metadata);
    }

}

