package com.example.myrok.domain;

import com.example.myrok.type.MemberProjectType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_member_project")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberProject extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mp_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "m_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_id")
    private Project project;
    @Column(name = "member_project_type")
    @Enumerated(EnumType.STRING)
    private MemberProjectType memberProjectType;
    public void changeMemberProjectType(MemberProjectType memberProjectType){
        this.memberProjectType = memberProjectType;
    }
}
