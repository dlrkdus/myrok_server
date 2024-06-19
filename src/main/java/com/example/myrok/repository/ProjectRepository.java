package com.example.myrok.repository;

import com.example.myrok.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByInviteCode(String inviteCode);
}
