package com.example.myrok.service;

import com.example.myrok.domain.Record;
import com.example.myrok.domain.RecordTag;

public interface RecordTagService {
    RecordTag save(Long projectId, Record record, String tagName);
    void delete(Long recordId);
}
