package com.example.myrok.dto.member;

import lombok.*;

public class MemberDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    @ToString
    public static class MemberNameDto{
        private Long memberId;
        private String name;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @ToString
    public static class MemberInformation{
        private String socialId;
        private String email;
        private String name;
        private String postUrl;
    }
}
