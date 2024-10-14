package com.example.myrok.service;

import com.example.myrok.domain.*;
import com.example.myrok.domain.Record;
import com.example.myrok.dto.project.DashBoardDTO;
import com.example.myrok.dto.project.TagCountDTO;
import com.example.myrok.dto.record.RecordResponseDTO;
import com.example.myrok.dto.project.TagDTO;
import com.example.myrok.dto.member.MemberDTO;
import com.example.myrok.dto.record.RecordDTO;
import com.example.myrok.dto.record.RecordClass;
import com.example.myrok.component.event.RecordSavedEvent;
import com.example.myrok.dto.pagination.PageRequestDto;
import com.example.myrok.dto.pagination.PageResponseDto;
import com.example.myrok.dto.record.RecordUpdateDTO;
import com.example.myrok.exception.CustomException;
import com.example.myrok.repository.*;
import com.example.myrok.type.ErrorCode;
import com.example.myrok.type.MemberProjectType;
import com.example.myrok.type.Role;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import java.util.NoSuchElementException;

import static com.example.myrok.type.MemberProjectType.PROJECT_MEMBER;

@RequiredArgsConstructor
@Service
public class RecordServiceImpl implements RecordService{

    @Autowired
    private final RecordRepository recordRepository;
    @Autowired
    private final ProjectRepository projectRepository;
    @Autowired
    private final MemberProjectRepository memberProjectRepository;

    private final MemberRepository memberRepository;

    @Autowired
    private RecordTagService recordTagService;
    @Autowired
    private MemberRecordService memberRecordService;
    @Autowired
    private MemberRecordRepository memberRecordRepository;
    @Autowired
    private RecordTagRepository recordTagRepository;

    private final ApplicationEventPublisher applicationEventPublisher;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Record save(RecordDTO recordDTO){

        // 멤버 리스트 & 태그 리스트 받아와서 Record 저장
        List<String> tags = recordDTO.getTagList();
        List<Long> members = recordDTO.getMemberList();
        Long projectId = recordDTO.getProjectId();
        Long recordWriterId=recordDTO.getRecordWriterId();

        // 작성자 아이디가 멤버 리스트에 없다면 예외
        if (!members.contains(recordWriterId)){
            throw new IllegalArgumentException("작성자 아이디가 멤버 리스트에 없습니다.");
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 프로젝트입니다. id: " + projectId));

        // 멤버가 프로젝트 소속인지 확인
        for (Long memberId : members){
            MemberProject memberProject = memberProjectRepository.findByMemberIdAndProjectIdAndMemberProjectType(memberId,projectId, PROJECT_MEMBER)
                    .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_IN_PROJECT, HttpStatus.BAD_REQUEST));
            // 탈퇴 여부도 확인.
            if (memberProject.getMemberProjectType()!=PROJECT_MEMBER){
                throw new CustomException(ErrorCode.NON_PROJECT_MEMBER_ERROR, HttpStatus.BAD_REQUEST);
            }
        }

        Record record=recordDTO.toEntity(project);
        Record savedRecord = recordRepository.save(record);

        // Tag 저장
        for (String tagName : tags) {
            recordTagService.save(projectId,savedRecord,tagName);
        }

        memberRecordService.save(members,savedRecord,recordWriterId);

        applicationEventPublisher.publishEvent(new RecordSavedEvent(savedRecord));
        return savedRecord;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUpdate(Long recordId, Long recordWriterId) {
        // 회의록 삭제
        Record record = recordRepository.findById(recordId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회의록입니다. id: " + recordId));
        // 이미 삭제된 회의록이면 예외
        if (record.getDeleted()) {
            throw new CustomException(ErrorCode.DELETED_RECORD_CODE, HttpStatus.BAD_REQUEST);
        }
        //회의 참여자가 아니라면 예외
        MemberRecord memberRecord =memberRecordRepository.findByMemberIdAndRecordIdAndDeletedIsFalse(recordWriterId,recordId)
                .orElseThrow(() -> new CustomException(ErrorCode.WRONG_RECORD_ACCESS, HttpStatus.BAD_REQUEST));
        //작성자 외 삭제 시도시 예외
        if (memberRecord.getRole()!=Role.ADMIN){
            throw new CustomException(ErrorCode.WRONG_DELETE_ACCESS, HttpStatus.BAD_REQUEST);
        }
        record.delete();
        recordRepository.save(record);

        //MemberRecord 매핑객체 삭제
        memberRecordService.delete(record.getId());

        //RecordTag 매핑객체 삭제
        recordTagService.delete(record.getId());

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Record update(Long recordId, RecordUpdateDTO recordUpdateDTO) {
        List<String> updatedTags = recordUpdateDTO.tagList();
        Long recordWriterId = recordUpdateDTO.recordWriterId();
        Record record = recordRepository.findById(recordId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회의록입니다. id: " + recordId));
        //삭제된 회의록이라면 예외
        if(record.getDeleted()){
            throw new CustomException(ErrorCode.DELETED_RECORD_CODE, HttpStatus.BAD_REQUEST);
        }
        //회의 참여자가 아니라면 예외
        MemberRecord memberRecord =memberRecordRepository.findByMemberIdAndRecordIdAndDeletedIsFalse(recordWriterId,recordId)
                .orElseThrow(() -> new CustomException(ErrorCode.WRONG_RECORD_ACCESS, HttpStatus.BAD_REQUEST));
        //작성자 외 수정 시도시 예외
        if (memberRecord.getRole()!=Role.ADMIN){
            throw new CustomException(ErrorCode.WRONG_UPDATE_ACCESS, HttpStatus.BAD_REQUEST);
        }
        //수정 로직

        //이전 태그들 삭제
        recordTagService.delete(record.getId());

        List<RecordTag> updateRecordTagList = new ArrayList<>();
        for (String tagName : updatedTags) {
            updateRecordTagList.add(recordTagService.save(record.getProject().getId(),record,tagName));
        }

        record.setRecordName(recordUpdateDTO.recordName());
        record.setRecordTagList(updateRecordTagList);

        return recordRepository.save(record);
    }

    @Override
    public RecordResponseDTO read(Long recordId){
        Record record = recordRepository.findById(recordId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회의록입니다. id: " + recordId));
        if (record.getDeleted()) {
            throw new CustomException(ErrorCode.DELETED_RECORD_CODE, HttpStatus.BAD_REQUEST);
        }
        List<String> tagList = recordTagRepository.findTagsByRecordIdAndDeletedIsFalse(recordId);
        List<MemberDTO.MemberNameDto> memberList = new ArrayList<>();
        List<Long> members = memberRecordRepository.findMemberIdByRecordIdAndDeletedIsFalse(recordId);
        for (Long memberId : members) {
            String memberName = memberRepository.findNameById(memberId);
            MemberDTO.MemberNameDto member = MemberDTO.MemberNameDto.builder()
                    .name(memberName)
                    .memberId(memberId)
                    .build();
            memberList.add(member);
        }
        RecordResponseDTO readRecord = RecordResponseDTO.builder()
                .recordId(record.getId())
                .recordName(record.getRecordName())
                .recordDate(record.getRecordDate())
                .recordWriterId(record.getRecordWriterId())
                .recordContent(record.getRecordContent())
                .tagList(tagList)
                .memberList(memberList)
                .tagList(tagList)
                .projectId(record.getProject().getId())
                .build();

        return readRecord;

    }



    @Override
    public List<RecordClass.RecordListObject> getRecords(Long projectId) {
        List<Record> recordList = recordRepository.findAllByProjectIdAndDeletedIsFalse(projectId);
        return recordList.stream()
                .map(record -> {
                    Member member = memberRepository.findById(record.getRecordWriterId()).orElseThrow(NoSuchElementException::new);
                    return RecordClass.RecordListObject.builder()
                            .recordId(record.getId())
                            .recordWriterName(member.getName())
                            .recordDate(String.valueOf(record.getRecordDate()))
                            .recordName(record.getRecordName())
                            .build();
                }).collect(Collectors.toList());
    }

    @Override
    public List<RecordClass.RecordListObject> getRecordsBySearch(String searchValue, String tagName, Long projectId) {
        List<Record> recordList = recordRepository.findAllBySearch(projectId, searchValue, tagName);
        return recordList.stream()
                .map(record -> {
                    Member member = memberRepository.findById(record.getRecordWriterId()).orElseThrow(NoSuchElementException::new);
                    return RecordClass.RecordListObject.builder()
                            .recordId(record.getId())
                            .recordWriterName(member.getName())
                            .recordDate(String.valueOf(record.getRecordDate()))
                            .recordName(record.getRecordName())
                            .build();
                }).collect(Collectors.toList());
    }

    @Override
    public PageResponseDto<RecordClass.RecordListObject> getRecords(PageRequestDto pageRequestDto, Long projectId) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage()-1,
                pageRequestDto.getSize(),
                Sort.by("recordDate").descending());

        Page<Object> result = recordRepository.selectList(pageable, projectId);

        List<RecordClass.RecordListObject> dtoList = result.getContent().stream().map(arr -> {
            RecordClass.RecordListObject recordListObject = new RecordClass.RecordListObject();

            Record record = (Record) arr;


            Member writer = memberRepository.findById(record.getRecordWriterId()).orElseThrow(NoSuchElementException::new);
            recordListObject = RecordClass.RecordListObject.builder()
                    .recordId(record.getId())
                    .recordDate(String.valueOf(record.getRecordDate()))
                    .recordName(record.getRecordName())
                    .recordWriterName(writer.getName())
                    .build();

            return recordListObject;
        }).collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        return PageResponseDto.<RecordClass.RecordListObject>withAll()
                .dtoList(dtoList)
                .total(totalCount)
                .pageRequestDto(pageRequestDto)
                .build();
    }

    @Override
    public PageResponseDto<RecordClass.RecordListObject> getRecordsBySearch(PageRequestDto pageRequestDto, String searchValue, String tagName, Long projectId) {
        PageResponseDto<RecordClass.RecordListObject> records = recordRepository.search(pageRequestDto, searchValue, tagName, projectId);

        return records;
    }

    @Override
    public DashBoardDTO.TagCountListDTO getTagList(Long projectId){
        List<TagCountDTO> tags = recordTagRepository.findTagNameAndCountByProjectIdAndDeletedIsFalseForRecord(projectId)
                .orElse(Collections.emptyList());

        long totalCount = 0;
        for(TagCountDTO tag : tags){
            totalCount+=tag.getCount();
        }
        DashBoardDTO.TagCountListDTO tagListDTO = DashBoardDTO.TagCountListDTO.builder()
                .totalCount(totalCount)
                .tagList(tags)
                .build();
        return tagListDTO;
    }


    public RecordClass.ResponseDTO getRecordSummary(Long recordId) {
        Record record = recordRepository.findById(recordId).orElseThrow(NoSuchElementException::new);
        RecordClass.ResponseDTO responseDTO = RecordClass.ResponseDTO.builder()
                .id(recordId)
                .summary(record.getRecordContentSummary())
                .build();
        return responseDTO;
    }

}
