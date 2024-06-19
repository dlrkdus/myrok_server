package com.example.myrok.domain;

import com.example.myrok.type.Role;
import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_member_record")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRecord extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mr_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r_id")
    private Record record;

    @Description("MemberRecord 매핑 삭제")
    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean deleted = false;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void delete(){
        this.deleted=true;
    }
}
