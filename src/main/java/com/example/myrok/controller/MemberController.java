package com.example.myrok.controller;

import com.example.myrok.domain.MemberProject;
import com.example.myrok.dto.member.MemberInfoResponse;
import com.example.myrok.dto.member.MemberProjectResponse;
import com.example.myrok.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myrok/me")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<MemberInfoResponse> getMyInformation(Principal principal) {
        final MemberInfoResponse myInformation = memberService.getMemberInformation(principal.getName());

        return ResponseEntity.ok(myInformation);
    }

    @GetMapping("/project")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<MemberProjectResponse> getMyProject(Principal principal) {
        MemberProjectResponse myProject = memberService.getMyProject(principal.getName());

        if (myProject == null) {
            // 프로젝트 정보가 없을 경우 빈 객체 반환
            return ResponseEntity.ok(new MemberProjectResponse());
        }

        return ResponseEntity.ok(myProject);
    }
}