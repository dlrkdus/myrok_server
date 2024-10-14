package com.example.myrok.service;


import com.example.myrok.dto.member.MemberInfoResponse;
import com.example.myrok.dto.member.MemberProjectResponse;
import jakarta.transaction.Transactional;

@Transactional
public interface MemberService {
    void checkMemberHaveProject(String socialId);
    Long participateProject(String socialId, String inviteCode);
    Long getOutFromProject(Long projectId, String socialId);
    public MemberInfoResponse getMemberInformation(String socialId);
    MemberProjectResponse getMyProject(String socialId);
}
