package com.example.myrok.service;

import com.example.myrok.domain.*;
import com.example.myrok.domain.Record;
import com.example.myrok.exception.CustomException;
import com.example.myrok.repository.MemberProjectRepository;
import com.example.myrok.repository.MemberRecordRepository;
import com.example.myrok.repository.MemberRepository;
import com.example.myrok.type.ErrorCode;
import com.example.myrok.type.MemberProjectType;
import com.example.myrok.type.Role;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberRecordServiceImpl implements MemberRecordService{
    @Autowired
    MemberRecordRepository memberRecordRepository;
    @Autowired
    MemberProjectRepository memberProjectRepository;
    @Autowired
    MemberRepository memberRepository;
    @Override
    public List<MemberRecord> save(List<Long> members, Record record, Long recordWriterId){
        List<MemberRecord> memberRecordList= new ArrayList<>();
        for (Long memberId : members) {
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 멤버입니다. id: " + memberId));

            // 멤버가 프로젝트 소속인지 확인
            Long projectId = record.getProject().getId();
            MemberProject memberProject =
                    memberProjectRepository.findByMemberIdAndProjectIdAndMemberProjectType(memberId,projectId,MemberProjectType.PROJECT_MEMBER)
                            .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_IN_PROJECT, HttpStatus.BAD_REQUEST));
            // 멤버별 권한 부여
            Role role = memberId.equals(recordWriterId) ? Role.ADMIN : Role.PARTICIPANT;
            MemberRecord memberRecord = MemberRecord.builder()
                    .record(record)
                    .member(member)
                    .role(role)
                    .build();
            MemberRecord savedMemberRecord = memberRecordRepository.save(memberRecord);
            memberRecordList.add(savedMemberRecord);
        }
        return memberRecordList;
    }
    @Override
    public void delete(Long id){
        List<MemberRecord> memberRecords = memberRecordRepository.findAllByRecordId(id);
        for (MemberRecord memberRecord : memberRecords) {
            if (memberRecord.getDeleted()){
                throw new CustomException(ErrorCode.DELETED_MR_CODE, HttpStatus.BAD_REQUEST);
            }
            memberRecord.delete();
            memberRecordRepository.save(memberRecord);
        }
    }
}
