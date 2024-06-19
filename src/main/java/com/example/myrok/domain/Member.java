package com.example.myrok.domain;

import com.example.myrok.type.LoginProvider;
import com.example.myrok.type.MemberProjectType;
import com.example.myrok.type.MemberRole;
import com.example.myrok.type.Role;
import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_id")
    private Long id;

    private String name;

    @Description("소셜로그인 인증 후 받는 로그인정보")
    @Column(name = "social_id")
    private String socialId;

    @Description("랜덤 패스워드 부야")
    private String password;

    @Description("탈퇴한 회원, true의 경우 탈퇴한 회원")
    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean deleted = false;

    @Column(name = "img_url")
    @Description("이미지 url")
    private String imgUrl;

    private String email;

    @OneToMany(mappedBy = "member")
    private List<MemberProject> memberProjects;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberRole> memberRoleList = new ArrayList<>();

    private LoginProvider loginProvider;
    public void addRole(MemberRole memberRole){
        memberRoleList.add(memberRole);
    }
    public void deleteRole(MemberRole memberRole){
        memberRoleList.remove(memberRole);
    }
}
