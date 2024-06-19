package com.example.myrok.service;

import com.example.myrok.domain.Member;
import com.example.myrok.domain.MemberProject;
import com.example.myrok.domain.Project;
import com.example.myrok.dto.member.MemberDTO;
import com.example.myrok.dto.project.ProjectDTO;
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

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor // property에 대한 의존성 주입
@Service
@Log4j2
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final MemberProjectRepository memberProjectRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @Override
    public Long createProject(ProjectDTO.RegisterProject requestDto, String socialId) {
        memberService.checkMemberHaveProject(socialId);
        return register(requestDto, socialId);
    }

    @Override
    public Long register(ProjectDTO.RegisterProject projectDto, String socialId) {
        if(Objects.equals(projectDto.getStartDate(), "") && Objects.equals(projectDto.getEndDate(), "")){
            projectDto.setStartDate("1000-01-01");
            projectDto.setEndDate("3000-01-01");
        }
        Project project = projectRepository.save(dtoToEntity(projectDto));

        Member member = memberRepository.findBySocialId(socialId).orElseThrow(NoSuchElementException::new);
        member.addRole(MemberRole.CREATOR);
        member.addRole(MemberRole.TEAMMEMBER);

        MemberProject memberProject = MemberProject.builder()
                .memberProjectType(MemberProjectType.PROJECT_MEMBER)
                .member(member)
                .project(project)
                .build();

        return memberProjectRepository.save(memberProject).getId();
    }

    @Override
    //멤버가 프로젝트 나가기를 할 때마다, 해당 프로젝트 내 소속된 인원이 없는지 확인 필요
    //소속된 멤버의 상태가 모두 NON_PROJECT_MEMBER일 경우
    public Long checkProjectDelete(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(NoSuchElementException::new);
        int count = (int) memberProjectRepository.findAllByProjectIdAndMemberProjectType(projectId, MemberProjectType.PROJECT_MEMBER).stream().count();
        //한명이라도 소속 멤버인 경우 delete하지 않는다
        if(count == 0)
               project.changeDeleted();
        projectRepository.save(project);
        return project.getId();
    }

    @Override
    public ProjectDTO.ProjectMembersDto getProjectMembers(Long projectId, String socialId) {

        Member member = memberRepository.findBySocialId(socialId).orElseThrow(NoSuchFieldError::new);
        if(!memberProjectRepository.existsMemberProjectByMemberIdAndProjectId(member.getId(), projectId)){
            throw new CustomException(ErrorCode.MEMBER_NOT_ACCEPTABLE, HttpStatus.NOT_ACCEPTABLE);
        }

        //해당 프로젝트 소속 인원의 정보 들고오기
        List<MemberDTO.MemberNameDto> memberNameDtos= memberProjectRepository.findAllByProjectIdAndMemberProjectType(projectId, MemberProjectType.PROJECT_MEMBER)
                .stream()
                .map(memberProject -> {
                    return MemberDTO.MemberNameDto.builder()
                            .memberId(memberProject.getMember().getId())
                            .name(memberProject.getMember().getName())
                            .build();
                }).collect(Collectors.toList());

        return ProjectDTO.ProjectMembersDto.builder()
                .projectMemberNames(memberNameDtos)
                .build();
    }


    @Override
    public ProjectDTO.ParticipateProject getInviteCode(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(NoSuchElementException::new);
        return ProjectDTO.ParticipateProject.builder()
                .inviteCode(project.getInviteCode())
                .build();
    }
}
