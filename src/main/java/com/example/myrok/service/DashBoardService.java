package com.example.myrok.service;


import com.example.myrok.dto.project.DashBoardDTO;

public interface DashBoardService {

    DashBoardDTO.TagResponseDTO getTagInfo(Long projectId);
}
