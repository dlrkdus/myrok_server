package com.example.myrok.type;

import lombok.Getter;

@Getter
public enum MemberProjectType {
    PROJECT_MEMBER("프로젝트멤버"),
    NON_PROJECT_MEMBER("프로젝트탈퇴멤버");

    private String value;

    MemberProjectType(String status) {
        this.value = status;
    }
}
