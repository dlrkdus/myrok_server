package com.example.myrok.controller;

import com.example.myrok.domain.Record;
import com.example.myrok.dto.project.DashBoardDTO;
import com.example.myrok.dto.record.RecordDeleteDTO;
import com.example.myrok.dto.record.RecordResponseDTO;
import com.example.myrok.dto.record.RecordClass;
import com.example.myrok.dto.record.RecordDeleteDTO;
import com.example.myrok.dto.record.RecordResponseDTO;
import com.example.myrok.dto.pagination.PageRequestDto;
import com.example.myrok.dto.pagination.PageResponseDto;
import com.example.myrok.dto.record.RecordDTO;
import com.example.myrok.dto.record.RecordUpdateDTO;
import com.example.myrok.service.RecordService;
import com.example.myrok.service.openapi.ChatCompletionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myrok")
public class RecordController {
    private final RecordService recordService;
    private final ChatCompletionService chatCompletionService;

    @Operation(
            summary = "회의록 저장하기",
            description = "회의록을 저장하기"
    )
    @ApiResponse(
            responseCode = "201",
            description = "회의록을 저장하였습니다"
    )
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'TEAMMEMBER')")
    @PostMapping("/records")
    public ResponseEntity<Map<String, Long>> save(@RequestBody @Valid RecordDTO recordDTO){
        recordDTO.setRecordContent(StringEscapeUtils.escapeJson(recordDTO.getRecordContent()));
        Record savedRecord=recordService.save(recordDTO);
        return new ResponseEntity<>(Map.of("recordId", savedRecord.getId()
        ), HttpStatus.CREATED);
    }

    @PostMapping("/records/delete/{recordId}")
    public ResponseEntity<Record> delete(@PathVariable("recordId") Long recordId, @RequestBody @Valid RecordDeleteDTO recordDeleteDTO){
        Long recordWriterId = recordDeleteDTO.recordWriterId();
        recordService.deleteUpdate(recordId,recordWriterId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "프로젝트 회의록 목록을 가져옵니다",
            description = "프로젝트 회의록 목록을 가져옵니다"
    )
    @ApiResponse(
            responseCode = "200",
            description = "프로젝트 회의록 목록을 가져왔습니다."
    )
    @GetMapping("/list")
    public ResponseEntity<List<RecordClass.RecordListObject>> getRecordList(Long projectId){
        return new ResponseEntity<>(recordService.getRecords(projectId), HttpStatus.OK);
    }
    @Operation(
            summary = "제목 혹은 태그명에 따른 회의록 목록을 가져옵니다.",
            description = "제목 혹은 태그명에 따른 회의록 목록을 가져옵니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "제목 혹은 태그명에 따른 회의록 목록을 가져옵니다."
    )
    @GetMapping("/{projectId}/list")
    public ResponseEntity<List<RecordClass.RecordListObject>> getRecordsByProjectNameOrTagName(@PathVariable Long projectId, @RequestParam(value = "recordName", required = false) String recordName, @RequestParam(value = "tagName", required = false) String tagName){
        return new ResponseEntity<>(recordService.getRecordsBySearch(recordName, tagName, projectId), HttpStatus.OK);
    }

    @Operation(
            summary = "프로젝트 회의록 목록을 페이징 처리하여 가져옵니다",
            description = "프로젝트 회의록 목록을 페이징 처리하여 가져옵니다"
    )
    @ApiResponse(
            responseCode = "200",
            description = "프로젝트 회의록 목록을 페이징 처리하여 가져옵니다."
    )
    @GetMapping("/list/pagination")
    public ResponseEntity<PageResponseDto<RecordClass.RecordListObject>> getRecordsPagination(PageRequestDto pageRequestDto, Long projectId){
        return new ResponseEntity<>(recordService.getRecords(pageRequestDto, projectId), HttpStatus.OK);
    }

    @Operation(
            summary = "제목 혹은 태그명에 따른 회의록 목록을 페이징 처리하여 가져옵니다.",
            description = "제목 혹은 태그명에 따른 회의록 목록을 페이징 처리하여 가져옵니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "제목 혹은 태그명에 따른 회의록 목록을 페이징 처리하여 가져옵니다."
    )
    @GetMapping("/{projectId}/list/pagination")
    public ResponseEntity<PageResponseDto<RecordClass.RecordListObject>> getRecordsPaginationByProjectNameOrTagName(PageRequestDto pageRequestDto, @PathVariable Long projectId, @RequestParam(value = "recordName", required = false) String recordName, @RequestParam(value = "tagName", required = false) String tagName){
        return new ResponseEntity<>(recordService.getRecordsBySearch(pageRequestDto, recordName, tagName, projectId), HttpStatus.OK);
    }

    @PatchMapping("/records/{recordId}")
    public ResponseEntity<Map<String, Long>> update( @PathVariable("recordId") Long recordId, @RequestBody @Valid RecordUpdateDTO recordUpdatedDTO){
        Record updatedRecord=recordService.update(recordId,recordUpdatedDTO);
        return new ResponseEntity<>(Map.of("recordId", updatedRecord.getId()), HttpStatus.CREATED);
    }

    @GetMapping("/records/{recordId}")
    public ResponseEntity<RecordResponseDTO> getRecord(@PathVariable("recordId") Long recordId){
        RecordResponseDTO readRecord = recordService.read(recordId);
        readRecord.setRecordContent(StringEscapeUtils.unescapeJson(readRecord.getRecordContent()));
        return new ResponseEntity<>(readRecord, HttpStatus.OK);
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "q")String question){
        if(!StringUtils.hasLength(question)){
            throw new RuntimeException();
        }
        return chatCompletionService.chatCompletions(question);
    }

    @GetMapping("{projectId}/tagList")
    public ResponseEntity<DashBoardDTO.TagCountListDTO> getTagList(@PathVariable("projectId") Long projectId){
        DashBoardDTO.TagCountListDTO tagListDTO= recordService.getTagList(projectId);
        return new ResponseEntity<>(tagListDTO, HttpStatus.OK);
    }
    @Operation(
            summary = "요약된 회의록을 가져옵니다.",
            description = "요약된 회의록을 가져옵니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "요약된 회의록을 가져왔습니다."
    )
    @GetMapping("/record/summary")
    public ResponseEntity<RecordClass.ResponseDTO> getSummary(Long recordId) {
        return new ResponseEntity<>(recordService.getRecordSummary(recordId), HttpStatus.CREATED);
    }

}
