package com.example.myrok.domain;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_record_tag")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordTag extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rt_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r_id")
    private Record record;

    private Long projectId;

    private String tagName;

    @Description("RecordTag 매핑 삭제")
    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean deleted = false;

    public void delete(){
        this.deleted=true;
    }



}
