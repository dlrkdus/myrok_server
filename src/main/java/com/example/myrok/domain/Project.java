package com.example.myrok.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jdk.jfr.Description;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_project")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private Long id;

    @NonNull
    @Column(name = "project_name")
    private String projectName;

    @Description("삭제된 프로젝트, true의 경우 삭제된 프로젝트")
    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean deleted = false;


    @Column(name = "start_date")
    @Description("프로젝트 시작 일자")
    private LocalDate startDate;

    @Column(name = "end_date")
    @Description("프로젝트 마감 일자")
    private LocalDate endDate;

    @Column(name = "invite_code")
    @Description("초대코드")
    @Builder.Default
    private String inviteCode = String.valueOf(UUID.randomUUID().toString().split("-")[0]);

    @Description("프로젝트 소속 회의록")
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Record> record = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<MemberProject> memberProjects;
    public void changeDeleted(){this.deleted = true;}
}
