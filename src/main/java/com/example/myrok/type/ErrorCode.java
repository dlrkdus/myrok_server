package com.example.myrok.type;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode implements EnumModel{
    // COMMON
    RESOURCE_NOT_FOUND(40400, "C001", "존재하지 않습니다."),
    INSUFFICIENT_VALID(40002, "C002", "불충분한 문장입니다."),
    INVALID_REQUEST_FORMAT(40003,"C003","회의록 형식이 잘못되었습니다."),

    //project
    MEMBER_IN_PROJECT(40600, "P001", "기존 프로젝트에서 나간 후 진행해주세요."),
    LIMITED_MEMBER(40601, "P002", "프로젝트 인원이 초과되었습니다."),
    MEMBER_NOT_ACCEPTABLE(40602, "P003", "해당 프로젝트에 대한 권한이 없습니다."),
    WRONG_INVITE_CODE(40401, "P004", "잘못된 참여코드 입니다."),
    MEMBER_NOT_IN_PROJECT(40603,"P005","해당 프로젝트에 소속되지 않은 멤버입니다."),
    NON_PROJECT_MEMBER_ERROR(40100,"P006","탈퇴된 멤버입니다."),
    DELETED_PROJECT(40406,"P007","삭제된 프로젝트입니다"),
    // record
    DELETED_RECORD_CODE(40402,"R001","이미 삭제된 회의록입니다."),
    WRONG_UPDATE_ACCESS(40604,"R002","회의록 수정은 작성자만 가능합니다."),
    WRONG_DELETE_ACCESS(40606,"R004","회의록 삭제는 작성자만 가능합니다."),
    WRONG_RECORD_ACCESS(40605,"R003","회의 참여자가 아닙니다."),
    // tag
    DELETED_TAG_CODE(40403,"T001","이미 삭제된 태그입니다."),
    // memberRecord
    DELETED_MR_CODE(40404,"MR001","이미 삭제된 MemberRecord 매핑입니다."),
    // recordTag
    DELETED_RT_CODE(40405,"RT001","이미 삭제된 RecordTag 매핑입니다.");



    private int status;
    private String code;
    private String message;
    private String detail;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    @Override
    public String getKey() {
        return this.code;
    }

    @Override
    public String getValue() {
        return this.message;
    }
}
