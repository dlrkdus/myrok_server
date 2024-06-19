package com.example.myrok.dto.record;

import com.example.myrok.domain.*;
import com.example.myrok.domain.Record;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordDTO {
    @NotBlank(message = "제목은 필수 입력 사항입니다.")
    private String recordName;
    @NotBlank(message = "본문은 필수 입력 사항입니다.")
    private String recordContent;
    private LocalDate recordDate;
    @NotNull
    private Long recordWriterId;
    @NotEmpty
    private List<Long> memberList;
    @Max(10)
    private List<String> tagList;
    @NotNull
    private Long projectId;



    public Record toEntity(Project project) {
        return Record.builder()
                .recordName(recordName)
                .recordContent(recordContent)
                .recordDate(recordDate)
                .recordWriterId(recordWriterId)
                .project(project)
                .build();
    }
}

