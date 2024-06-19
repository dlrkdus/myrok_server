package com.example.myrok.repository.search;

import com.example.myrok.domain.Member;
import com.example.myrok.domain.QRecord;
import com.example.myrok.domain.QRecordTag;
import com.example.myrok.domain.Record;
import com.example.myrok.dto.record.RecordClass;
import com.example.myrok.dto.pagination.PageRequestDto;
import com.example.myrok.dto.pagination.PageResponseDto;
import com.example.myrok.repository.MemberRepository;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class RecordSearchImpl extends QuerydslRepositorySupport implements RecordSearch{

    @Autowired
    MemberRepository memberRepository;

    public RecordSearchImpl() {
        super(Record.class);
    }

    @Override
    public PageResponseDto<RecordClass.RecordListObject> search(PageRequestDto pageRequestDto, String searchValue, String tagValue, Long projectId) {
        QRecord record = QRecord.record;
        QRecordTag recordTag = QRecordTag.recordTag;

        JPQLQuery<Record> query = from(record);
        query.where(record.project.id.eq(projectId).and(record.deleted.eq(false)));

        if (searchValue!= null &&!searchValue.isEmpty()) {
            query.where(record.recordName.like("%" + searchValue + "%"));
        }
        if (tagValue!= null &&!tagValue.isEmpty()) {
            query.select(record)
                    .from(record)
                    .leftJoin(record.recordTagList, recordTag).fetchJoin()
                    .where(recordTag.tagName.eq(tagValue))
                    .fetch();
        }


        Pageable pageable = PageRequest.of(pageRequestDto.getPage() -1, pageRequestDto.getSize(), Sort.by("recordDate").descending());

        this.getQuerydsl().applyPagination(pageable,query);

        List<Record> list = query.fetch(); //목록 데이터

        long total = query.fetchCount();

        List<RecordClass.RecordListObject> recordListObjects = list.stream().map(record1 -> {
            Member member = memberRepository.findById(record1.getRecordWriterId()).orElseThrow(NoSuchElementException::new);
           return RecordClass.RecordListObject.builder()
                    .recordId(record1.getId())
                    .recordDate(String.valueOf(record1.getRecordDate()))
                    .recordName(record1.getRecordName())
                    .recordWriterName(member.getName())
                    .build();
        }).collect(Collectors.toList());

        PageResponseDto<RecordClass.RecordListObject> responseDto = PageResponseDto.<RecordClass.RecordListObject>withAll()
                .dtoList(recordListObjects)
                .total(total)
                .pageRequestDto(pageRequestDto)
                .build();

        return responseDto;
    }
}
