package com.example.myrok.dto.record;

import com.example.myrok.dto.member.MemberDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class RecordResponseDTO {
    private Long recordId;
    private String recordName;
    private String recordContent;
    private LocalDate recordDate;
    private Long recordWriterId;
    private List<MemberDTO.MemberNameDto> memberList;
    private List<String> tagList;
    private Long projectId;
}
