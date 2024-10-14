package com.example.myrok.repository;

import com.example.myrok.domain.Member;
import com.example.myrok.domain.MemberProject;
import com.example.myrok.type.MemberProjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberProjectRepository extends JpaRepository<MemberProject, Long> {
    Optional<MemberProject> findByMember(Member member);
    Optional<MemberProject> findByMemberAndMemberProjectType(Member member, MemberProjectType memberProjectType);
    Optional<MemberProject> findByMemberIdAndProjectId(Long memberId, Long projectId);
    Optional<MemberProject> findByMemberIdAndProjectIdAndMemberProjectType(Long memberId, Long projectId, MemberProjectType memberProjectType);
    Optional<MemberProject> findByMemberAndProjectIdAndMemberProjectType(Member member, Long projectId, MemberProjectType memberProjectType);

    List<MemberProject> findAllByProjectIdAndMemberProjectType(Long projectId, MemberProjectType memberProjectType);
    Boolean existsMemberProjectByMemberIdAndProjectId(Long memberId, Long projectId);
    List<MemberProject> findAllByMemberId(Long memberId);
}
