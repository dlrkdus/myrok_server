package com.example.myrok.controller;

import com.example.myrok.dto.project.DashBoardDTO;
import com.example.myrok.service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myrok")
public class DashBoardController {

    @Autowired
    private DashBoardService dashBoardService;

    @GetMapping("/{projectId}/dashboard")
    public ResponseEntity<DashBoardDTO.TagResponseDTO> getTagInfo(@PathVariable("projectId") Long projectId){
        DashBoardDTO.TagResponseDTO tagResponse = dashBoardService.getTagInfo(projectId);
        return new ResponseEntity<>(tagResponse, HttpStatus.OK);
    }
}
