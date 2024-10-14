package com.example.myrok.dto.member;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // null 필드는 포함하지 않도록 설정
public class MemberProjectResponse{
    private Long projectId;
    private String projectName;
    private String startDate;
    private String endDate;
}