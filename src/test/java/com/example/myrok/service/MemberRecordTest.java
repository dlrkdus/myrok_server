package com.example.myrok.service;

import com.example.myrok.domain.*;
import com.example.myrok.domain.Record;
import com.example.myrok.exception.CustomException;
import com.example.myrok.repository.MemberProjectRepository;
import com.example.myrok.repository.MemberRecordRepository;
import com.example.myrok.repository.MemberRepository;
import com.example.myrok.type.MemberProjectType;
import com.example.myrok.type.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberRecordTest {

    @Autowired
    MemberRecordServiceImpl memberRecordService;
    @MockBean
    private MemberRepository memberRepository;
    @MockBean
    private MemberProjectRepository memberProjectRepository;
    @MockBean
    private MemberRecordRepository memberRecordRepository;

    @Captor
    private ArgumentCaptor<MemberRecord> memberRecordCaptor;

    private final Long projectId = 1L;
    private final Long memberId1 = 1L;
    private final Long memberId2 = 2L;
    private Project mockProject;
    private Member mockMember1, mockMember2;
    private Record mockRecord;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this); // ArgumentCaptor 초기화

        // 공통 객체 초기화
        mockProject = Project.builder().id(projectId).projectName("프로젝트").build();
        mockMember1 = Member.builder().id(memberId1).name("김민서").build();
        mockMember2 = Member.builder().id(memberId2).name("최민서").build();
        mockRecord = mock(Record.class);

        when(mockRecord.getProject()).thenReturn(mockProject);
        when(memberRepository.findById(memberId1)).thenReturn(Optional.of(mockMember1));
        when(memberRepository.findById(memberId2)).thenReturn(Optional.of(mockMember2));
    }

    private void setupMemberProjectMocks(Member member, MemberProjectType type) {
        MemberProject mockMemberProject = mock(MemberProject.class);
        when(mockMemberProject.getMember()).thenReturn(member);
        when(mockMemberProject.getProject()).thenReturn(mockProject);
        when(mockMemberProject.getMemberProjectType()).thenReturn(type);
        when(memberProjectRepository.findByMember(member)).thenReturn(Optional.of(mockMemberProject));
    }

    private void setupMemberProjectTypeMocks(Member member, Long projectId, MemberProjectType type) {
        MemberProject mockMemberProject = mock(MemberProject.class);
        when(mockMemberProject.getMember()).thenReturn(member);
        when(mockMemberProject.getProject()).thenReturn(mockProject);
        when(mockMemberProject.getMemberProjectType()).thenReturn(MemberProjectType.PROJECT_MEMBER);
        when(memberProjectRepository.findByMemberIdAndProjectIdAndMemberProjectType(member.getId(), projectId, type)).thenReturn(Optional.of(mockMemberProject));


    }

    @Test
    @DisplayName("Member 와 Record 관계 저장")
    void saveMemberRecordSuccess() {
        // 캡처 쓰지 말고 목 객체 만들어서 값 비교하자 TagTest의 saveNewTag처럼 (근데 졸려서 나중에 할래..)
        setupMemberProjectMocks(mockMember1, MemberProjectType.PROJECT_MEMBER);
        setupMemberProjectMocks(mockMember2, MemberProjectType.PROJECT_MEMBER);
        setupMemberProjectTypeMocks(mockMember1, projectId, MemberProjectType.PROJECT_MEMBER);
        setupMemberProjectTypeMocks(mockMember2, projectId, MemberProjectType.PROJECT_MEMBER);

        List<Long> memberList = List.of(memberId1, memberId2);

        // When
        memberRecordService.save(memberList, mockRecord, memberId1);

        // Then
        verify(memberRecordRepository, times(memberList.size())).save(memberRecordCaptor.capture());

        List<MemberRecord> capturedMemberRecords = memberRecordCaptor.getAllValues();
        if (!capturedMemberRecords.isEmpty()) {
            assertEquals(Role.ADMIN, capturedMemberRecords.get(0).getRole());
            assertEquals(Role.PARTICIPANT, capturedMemberRecords.get(1).getRole());
        } else {
            fail("No MemberRecord was saved.");
        }
    }

    @Test
    @DisplayName("멤버가 해당 프로젝트 소속이지 않거나 탈퇴된 멤버일 때")
    void saveMemberRecordNotInProjectOrNON_PROJECT_MEMBERThrowsException() {
        setupMemberProjectMocks(mockMember1, MemberProjectType.NON_PROJECT_MEMBER);
        setupMemberProjectMocks(mockMember2, MemberProjectType.PROJECT_MEMBER);

        List<Long> memberList = List.of(memberId1, memberId2);

        // When & Then
        assertThrows(CustomException.class, () -> memberRecordService.save(memberList, mockRecord, memberId1));
        verify(memberRecordRepository, never()).save(any(MemberRecord.class));
    }

    @Test
    void delete() {
        // 해당 테스트 케이스 구현
    }

}