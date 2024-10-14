package com.example.myrok.service;

import com.example.myrok.domain.Member;
import com.example.myrok.domain.MemberProject;
import com.example.myrok.domain.Project;
import com.example.myrok.dto.member.MemberInfoResponse;
import com.example.myrok.dto.member.MemberProjectResponse;
import com.example.myrok.exception.CustomException;
import com.example.myrok.repository.MemberProjectRepository;
import com.example.myrok.repository.MemberRepository;
import com.example.myrok.repository.ProjectRepository;
import com.example.myrok.type.ErrorCode;
import com.example.myrok.type.MemberProjectType;
import com.example.myrok.type.MemberRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final MemberProjectRepository memberProjectRepository;

    @Override
    public void checkMemberHaveProject(String socialId) {
        Member member = memberRepository.findBySocialId(socialId).orElseThrow(NoSuchElementException::new);
        Optional<MemberProject> memberProject = memberProjectRepository.findByMemberAndMemberProjectType(member, MemberProjectType.PROJECT_MEMBER);
        if(memberProject.isPresent())
            throw new CustomException(ErrorCode.MEMBER_IN_PROJECT, HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public Long participateProject(String socialId, String inviteCode) {

        Optional<Project> project = projectRepository.findByInviteCode(inviteCode);
        if(project.isEmpty()){
            throw new CustomException(ErrorCode.WRONG_INVITE_CODE, HttpStatus.BAD_REQUEST);
        }
        if(project.get().getDeleted()){
            throw new CustomException(ErrorCode.DELETED_PROJECT, HttpStatus.BAD_GATEWAY);
        }

        checkMemberHaveProject(socialId);

        Member member = memberRepository.findBySocialId(socialId).orElseThrow(NoSuchElementException::new);
        member.addRole(MemberRole.TEAMMEMBER);

        MemberProject memberProject = MemberProject.builder()
                .member(member)
                .project(project.get())
                .memberProjectType(MemberProjectType.PROJECT_MEMBER)
                .build();

        memberProjectRepository.save(memberProject);
        return project.get().getId();
    }

    @Override
    public Long getOutFromProject(Long projectId, String socialId) {
        Member member = memberRepository.findBySocialId(socialId).orElseThrow(NoSuchElementException::new);
        member.deleteRole(MemberRole.TEAMMEMBER);
        if(member.getMemberRoleList().contains(MemberRole.CREATOR)){
            member.deleteRole(MemberRole.CREATOR);
        }

        MemberProject memberProject = memberProjectRepository.findByMemberAndProjectIdAndMemberProjectType(member, projectId, MemberProjectType.PROJECT_MEMBER).orElseThrow(new CustomException(ErrorCode.MEMBER_NOT_ACCEPTABLE, HttpStatus.NOT_ACCEPTABLE));
        memberProject.changeMemberProjectType(MemberProjectType.NON_PROJECT_MEMBER);
        return memberProjectRepository.save(memberProject).getId();
    }

    public MemberInfoResponse getMemberInformation(String socialId) {
        Member member = memberRepository.findBySocialId(socialId).orElseThrow(NoSuchFieldError::new);

        return MemberInfoResponse.of(member);
    }

    public MemberProjectResponse getMyProject(String socialId) {
        Member member = memberRepository.findBySocialId(socialId).orElse(null);

        if (member == null) {
            // Member가 없을 경우 빈 객체 반환
            return MemberProjectResponse.builder().build();
        }

        MemberProject memberProject = memberProjectRepository.findByMemberAndMemberProjectType(member, MemberProjectType.PROJECT_MEMBER).orElse(null);

        if (memberProject == null) {
            // MemberProject가 없을 경우 빈 객체 반환
            return MemberProjectResponse.builder().build();
        }

        // MemberProject가 있을 경우 해당 데이터로 응답 객체 생성
        return MemberProjectResponse.builder()
                .projectId(memberProject.getProject().getId())
                .projectName(memberProject.getProjectName())
                .startDate(String.valueOf(memberProject.getProject().getStartDate()))
                .endDate(String.valueOf(memberProject.getProject().getEndDate()))
                .build();
    }
}
