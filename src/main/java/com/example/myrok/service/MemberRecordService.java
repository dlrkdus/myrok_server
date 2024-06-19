package com.example.myrok.service;

import com.example.myrok.domain.MemberRecord;
import com.example.myrok.domain.Record;
import com.example.myrok.type.Role;

import java.util.List;

public interface MemberRecordService {
    List<MemberRecord> save(List<Long> members, Record record, Long recordWriterId);
    void delete(Long id);
}
