package com.example.myrok.service;

import com.example.myrok.domain.Project;
import com.example.myrok.dto.project.ProjectDTO;
import jakarta.transaction.Transactional;

import java.time.LocalDate;

@Transactional
public interface ProjectService {
    Long createProject(ProjectDTO.RegisterProject requestDto , String socialId);
    Long register(ProjectDTO.RegisterProject requestDto, String socialId);
    Long checkProjectDelete(Long projectId);

    ProjectDTO.ProjectMembersDto getProjectMembers(Long projectId, String socialId);
    ProjectDTO.ParticipateProject getInviteCode(Long projectId);

    default Project dtoToEntity(ProjectDTO.RegisterProject projectDto){
        return Project.builder()
                .projectName(projectDto.getProjectName())
                .startDate(LocalDate.parse(projectDto.getStartDate()))
                .endDate(LocalDate.parse(projectDto.getEndDate()))
                .build();
    }

    default ProjectDTO.RegisterProject entityToDto(Project project){
        return ProjectDTO.RegisterProject.builder()
                .projectName(project.getProjectName())
                .startDate(String.valueOf(project.getStartDate()))
                .endDate(String.valueOf(project.getEndDate()))
                .build();
    }
}
