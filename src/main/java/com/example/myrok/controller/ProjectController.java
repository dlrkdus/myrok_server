package com.example.myrok.controller;

import com.example.myrok.dto.project.ProjectDTO;
import com.example.myrok.service.MemberService;
import com.example.myrok.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Tag(name = "Project", description = "Project 관련 API 입니다.")
@RequestMapping("myrok/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final MemberService memberService;

    @Operation(
            summary = "프로젝트 생성",
            description = "프로젝트를 생성합니다"
    )
    @ApiResponse(
            responseCode = "200",
            description = "프로젝트 생성을 완료하였습니다."
    )
    @PostMapping("")
    @Transactional
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<Long> createProject(@RequestBody ProjectDTO.RegisterProject projectDto, Principal principal){
        return ResponseEntity.ok().body(projectService.createProject(projectDto , principal.getName()));
    }


    @Operation(
            summary = "프로젝트 초대코드를 가져옵니다.",
            description = "프로젝트 초대코드를 가져옵니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "프로젝트 초대코드를 가져옵니다."
    )
    @GetMapping("/{projectId}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<ProjectDTO.ParticipateProject> getInviteCode(@PathVariable Long projectId) {
        return ResponseEntity.ok().body(projectService.getInviteCode(projectId));
    }

    @Operation(
            summary = "프로젝트 참가",
            description = "프로젝트에 참가합니다"
    )
    @ApiResponse(
            responseCode = "200",
            description = "프로젝트 참가를 완료하였습니다."
    )
    @PostMapping("/participate")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<Long> participateProject(@RequestBody ProjectDTO.ParticipateProject projectDto, Principal principal){
        memberService.checkMemberHaveProject(principal.getName());
        return ResponseEntity.ok().body(memberService.participateProject(principal.getName(), projectDto.getInviteCode()));
    }
    @Operation(
            summary = "프로젝트 나가기",
            description = "프로젝트에서 나갑니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "프로젝트에서 나갔습니다."
    )
    @DeleteMapping("")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'TEAMMEMBER')")
    public ResponseEntity<Long> getOutProject(@RequestBody ProjectDTO.ProjectMemberDto dto, Principal principal) {
        memberService.getOutFromProject(dto.getProjectId(), principal.getName());
        Long id = projectService.checkProjectDelete(dto.getProjectId());
        return ResponseEntity.ok().body(id);
    }

    @Operation(
            summary = "프로젝트 멤버를 가져옵니다",
            description = "프로젝트 멤버를 가져옵니다"
    )
    @ApiResponse(
            responseCode = "200",
            description = "프로젝트 멤버를 가져왔습니다."
    )
    @GetMapping("/{projectId}/members")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'TEAMMEMBER')")
    public ResponseEntity<ProjectDTO.ProjectMembersDto> getProjectMembers(@PathVariable Long projectId, Principal principal) {
        return ResponseEntity.ok().body(projectService.getProjectMembers(projectId, principal.getName()));
    }
}
