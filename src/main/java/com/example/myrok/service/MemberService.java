package com.example.myrok.service;

import jakarta.transaction.Transactional;

@Transactional
public interface MemberService {
    void checkMemberHaveProject(String socialId);
    Long participateProject(String socialId, String inviteCode);
    Long getOutFromProject(Long memberId, Long projectId, String socialId);
}
