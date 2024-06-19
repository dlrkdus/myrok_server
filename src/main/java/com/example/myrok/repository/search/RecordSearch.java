package com.example.myrok.repository.search;

import com.example.myrok.dto.record.RecordClass;
import com.example.myrok.dto.pagination.PageRequestDto;
import com.example.myrok.dto.pagination.PageResponseDto;

public interface RecordSearch {
    PageResponseDto<RecordClass.RecordListObject> search(PageRequestDto pageRequestDto, String searchValue, String tagValue, Long projectId);
}
