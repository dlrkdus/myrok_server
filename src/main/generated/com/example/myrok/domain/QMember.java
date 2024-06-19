package com.example.myrok.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 898207079L;

    public static final QMember member = new QMember("member1");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DatePath<java.time.LocalDate> createdDate = _super.createdDate;

    public final BooleanPath deleted = createBoolean("deleted");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    //inherited
    public final DatePath<java.time.LocalDate> lastModifiedDate = _super.lastModifiedDate;

    public final EnumPath<com.example.myrok.type.LoginProvider> loginProvider = createEnum("loginProvider", com.example.myrok.type.LoginProvider.class);

    public final ListPath<MemberProject, QMemberProject> memberProjects = this.<MemberProject, QMemberProject>createList("memberProjects", MemberProject.class, QMemberProject.class, PathInits.DIRECT2);

    public final ListPath<com.example.myrok.type.MemberRole, EnumPath<com.example.myrok.type.MemberRole>> memberRoleList = this.<com.example.myrok.type.MemberRole, EnumPath<com.example.myrok.type.MemberRole>>createList("memberRoleList", com.example.myrok.type.MemberRole.class, EnumPath.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath socialId = createString("socialId");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

