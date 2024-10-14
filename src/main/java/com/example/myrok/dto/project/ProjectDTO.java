package com.example.myrok.dto.project;

import com.example.myrok.dto.member.MemberDTO;
import lombok.*;

import java.util.List;

public class ProjectDTO {
    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class RegisterProject{
        private String projectName;
        @NonNull
        private String endDate;
        @NonNull
        private String startDate;
    }

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class ParticipateProject{
        @NonNull
        private String inviteCode;
    }

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class ProjectMemberDto{
        @NonNull
        private Long projectId;
    }


    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    @ToString
    public static class ProjectMembersDto{
        private List<MemberDTO.MemberNameDto> projectMemberNames;
    }

}
