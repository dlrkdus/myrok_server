package com.example.myrok.service;

import com.example.myrok.domain.Record;
import com.example.myrok.dto.project.DashBoardDTO;
import com.example.myrok.dto.record.RecordDTO;
import com.example.myrok.dto.record.RecordClass;
import com.example.myrok.dto.pagination.PageRequestDto;
import com.example.myrok.dto.pagination.PageResponseDto;

import java.util.List;

import com.example.myrok.dto.record.RecordDTO;
import com.example.myrok.dto.record.RecordResponseDTO;
import com.example.myrok.dto.record.RecordUpdateDTO;

public interface RecordService {
    Record save(RecordDTO recordDTO);

    void deleteUpdate(Long recordId,Long recordWriterId);

    Record update(Long recordId, RecordUpdateDTO recordDTO);

    RecordResponseDTO read(Long recordId);
    List<RecordClass.RecordListObject> getRecords(Long projectId);
    List<RecordClass.RecordListObject> getRecordsBySearch(String searchValue, String tagName, Long projectId);

    PageResponseDto<RecordClass.RecordListObject> getRecords(PageRequestDto pageRequestDto, Long projectId);

    PageResponseDto<RecordClass.RecordListObject> getRecordsBySearch(PageRequestDto pageRequestDto, String searchValue, String tagName, Long projectId);

    DashBoardDTO.TagCountListDTO getTagList(Long projectId);

    RecordClass.ResponseDTO getRecordSummary(Long recordId);

}
