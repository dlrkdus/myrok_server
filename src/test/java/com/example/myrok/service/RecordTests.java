package com.example.myrok.service;

import com.example.myrok.domain.*;
import com.example.myrok.domain.Record;
import com.example.myrok.dto.record.RecordUpdateDTO;
import com.example.myrok.dto.record.RecordDTO;
import com.example.myrok.exception.CustomException;
import com.example.myrok.repository.*;
import com.example.myrok.type.ErrorCode;
import com.example.myrok.type.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

import static com.example.myrok.type.MemberProjectType.PROJECT_MEMBER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RecordTests {

    @Autowired
    protected MockMvc mockMVC;

    @Autowired
    private RecordServiceImpl recordService;

    @MockBean
    private RecordRepository recordRepository;
    @MockBean
    private ProjectRepository projectRepository;
    @MockBean
    private MemberProjectRepository memberProjectRepository;
    @MockBean
    private MemberRepository memberRepository;
    @MockBean
    private MemberRecordRepository memberRecordRepository;
    @MockBean
    private MemberRecordServiceImpl memberRecordService;
    @MockBean
    private RecordTagServiceImpl recordTagService;

    // 이 테스트 클래스는 RecordService 의 로직만 취급함.
    // When 에는 RecordService, Then 에는 RecordRepository 가 들어감
    // 다른 클래스 로직들은 해당 테스트에서 검증할 것이므로 Service 로 사용.

    private Long recordId;
    private Long projectId, memberId1, memberId2, recordWriterId;
    private List<Long> memberList;
    private List<String> tagList;
    private List<String> updatedTagList;
    private List<RecordTag> mockRecordTagList = new ArrayList<>();
    private Project mockProject;
    private Member mockMember1, mockMember2;
    private MemberProject mockMemberProject1, mockMemberProject2;
    private RecordTag mockRecordTag1, mockRecordTag2;
    private Record mockRecord;
    private MemberRecord mockPARTICIPANTMemberRecord;
    private MemberRecord mockADMINMemberRecord;
    private RecordDTO mockRecordDTO;
    private RecordUpdateDTO mockRecordUpdateDTO;

    @BeforeEach
    void setUp() {
        // 초기화 코드
        projectId = 1L;
        memberId1 = 1L;
        memberId2 = 2L;
        recordId = 1L;

        memberList = List.of(memberId1, memberId2);
        tagList = List.of("tag1", "tag2");
        updatedTagList = List.of("tag3","tag4","tag5");
        recordWriterId = memberId1;

        mockProject = Project.builder()
                .id(projectId)
                .projectName("프로젝트")
                .build();
        mockMember1 = Member.builder()
                .id(memberId1)
                .name("김민서")
                .build();
        mockMember2 = Member.builder()
                .id(memberId2)
                .name("임지연")
                .build();
        mockRecordTag1 = RecordTag.builder()
                .record(mockRecord)
                .tagName("#tag1")
                .build();
        mockRecordTag2 = RecordTag.builder()
                .record(mockRecord)
                .tagName("#tag2")
                .build();
        mockRecordUpdateDTO = new RecordUpdateDTO(
                "Update Record",
                recordWriterId,
                updatedTagList
        );
        mockRecordTagList.add(mockRecordTag1);
        mockRecordTagList.add(mockRecordTag2);
        mockMemberProject1 = new MemberProject(1L, mockMember1, mockProject, PROJECT_MEMBER);
        mockMemberProject2 = new MemberProject(2L, mockMember2, mockProject, PROJECT_MEMBER);
        mockPARTICIPANTMemberRecord = MemberRecord.builder()
                .record(mockRecord)
                .member(mockMember2)
                .role(Role.PARTICIPANT)
                .deleted(false)
                .build();
        mockADMINMemberRecord = MemberRecord.builder()
                .record(mockRecord)
                .member(mockMember1)
                .role(Role.ADMIN)
                .deleted(false)
                .build();

        mockRecord = new Record();
        mockRecord.setId(recordId);
        mockRecord.setRecordTagList(mockRecordTagList);
        mockRecord.setProject(mockProject);

        // Mock 설정
        when(memberRepository.findById(memberId1)).thenReturn(Optional.of(mockMember1));
        when(memberRepository.findById(memberId2)).thenReturn(Optional.of(mockMember2));
        when(memberProjectRepository.findByMemberIdAndProjectId(memberId1, projectId)).thenReturn(Optional.of(mockMemberProject1));
        when(memberProjectRepository.findByMemberIdAndProjectId(memberId2, projectId)).thenReturn(Optional.of(mockMemberProject2));
        when(recordRepository.save(any(Record.class))).thenReturn(mockRecord);
    }

    @Transactional(rollbackFor = Exception.class)
    @DisplayName("회의록 저장 검사")
    @Test
    public void saveRecordSuccess() {
        // Given
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(mockProject));
        mockRecordDTO = new RecordDTO(
                "Test Record",
                "This is a test record.",
                LocalDate.now(),
                projectId,
                memberList,
                tagList,
                recordWriterId
        );
        //When
        Record savedRecord=recordService.save(mockRecordDTO);
        //Then
        assertNotNull(savedRecord);
        verify(projectRepository, times(1)).findById(anyLong());
        verify(recordRepository, times(1)).save(any(Record.class));
        verify(memberRecordService, times(1)).save(anyList(), eq(savedRecord), anyLong());
        verify(recordTagService, times(tagList.size())).save(anyLong(),any(Record.class),anyString());
    }


    @Test
    @DisplayName("회의록 Soft delete 검사")
    public void deleteRecordSuccess() {
        // Given
        when(recordRepository.findById(recordId)).thenReturn(Optional.of(mockRecord));
        // When
        recordService.deleteUpdate(recordId, 2L);
        // Then
        //times() : 메소드가 호출된 횟수
        verify(recordRepository, times(1)).findById(recordId);
        verify(recordRepository, times(1)).save(mockRecord);
        verify(memberRecordService, times(1)).delete(recordId);
        verify(recordTagService, times(1)).delete(recordId);
        assertTrue(mockRecord.getDeleted());
    }

    @Test
    @DisplayName("이미 삭제된 회의록 삭제 시도 검사")
    public void deleteAlreadyDeletedRecordThrowsException() {
        // Given
        when(recordRepository.findById(recordId)).thenReturn(Optional.of(mockRecord));
        mockRecord.delete(); // 이미 삭제된 상태로 설정
        // When
        // CustomException 이 던져지는지 assert
        CustomException customException = assertThrows(CustomException.class, () -> recordService.deleteUpdate(recordId, 2L));
        //Then
        // never() : 메소드가 실행되지 않았음을 검증. 삭제된 회의록의 삭제 시도니 관련 객체들도 삭제되면 안됨
        assertEquals(ErrorCode.DELETED_RECORD_CODE, customException.getErrorCode()); // 에러 코드 검증
        System.out.println("에러 코드: " + customException.getErrorCode()); // 에러 코드 로그로 출력

        verify(recordRepository, times(1)).findById(recordId);
        verify(recordRepository, never()).save(any(Record.class));
        verify(memberRecordService, never()).delete(anyLong());
        verify(recordTagService, never()).delete(anyLong());
    }

    @Test
    @DisplayName("회의록 수정 검사")
    public void updateRecordSuccess(){
        //Given
        when(memberRecordRepository.findByMemberIdAndRecordIdAndDeletedIsFalse(recordWriterId,recordId)).thenReturn(Optional.of(mockADMINMemberRecord));
        when(recordRepository.findById(recordId)).thenReturn(Optional.of(mockRecord));
        //When
        Record updatedRecord = recordService.update(recordId,mockRecordUpdateDTO);
        //Then
        assertNotNull(updatedRecord);
        assertEquals(mockRecordUpdateDTO.tagList().size(), updatedRecord.getRecordTagList().size());
        verify(recordRepository, times(1)).findById(recordId);
        verify(recordRepository, times(1)).save(any(Record.class));
        verify(recordTagService,times(1)).delete(recordId);
        verify(recordTagService, times(mockRecordUpdateDTO.tagList().size())).save(anyLong(), any(Record.class), anyString());

    }

    @Test
    @DisplayName("이미 삭제된 회의록 수정 시도 검사")
    public void updateAlreadyDeletedRecordThrowsException() {
        // Given
        when(memberRecordRepository.findByMemberIdAndRecordIdAndDeletedIsFalse(recordWriterId,recordId)).thenReturn(Optional.of(mockADMINMemberRecord));
        when(recordRepository.findById(recordId)).thenReturn(Optional.of(mockRecord));
        mockRecord.delete(); // 이미 삭제된 상태로 설정
        // When
        // CustomException 이 던져지는지 assert
        CustomException customException = assertThrows(CustomException.class, () -> recordService.update(recordId,mockRecordUpdateDTO));
        //Then
        // never() : 메소드가 실행되지 않았음을 검증. 삭제된 회의록의 삭제 시도니 관련 객체들도 삭제되면 안됨
        assertEquals(ErrorCode.DELETED_RECORD_CODE, customException.getErrorCode()); // 에러 코드 검증
        System.out.println("에러 코드: " + customException.getErrorCode()); // 에러 코드 로그로 출력

        verify(recordRepository, times(1)).findById(recordId);
        verify(recordRepository, never()).save(any(Record.class));
        verify(recordTagService, never()).delete(anyLong());
        verify(recordTagService, never()).save(anyLong(), any(Record.class), anyString());

    }

    @Test
    @DisplayName("작성자 외 수정 시도")
    public void updateRecordThrowsException() {
        //Given
        when(memberRecordRepository.findByMemberIdAndRecordIdAndDeletedIsFalse(recordWriterId,recordId)).thenReturn(Optional.of(mockPARTICIPANTMemberRecord));
        when(recordRepository.findById(recordId)).thenReturn(Optional.of(mockRecord));
        //When
        CustomException customException = assertThrows(CustomException.class, () -> recordService.update(recordId,mockRecordUpdateDTO));
        //Then
        assertEquals(ErrorCode.WRONG_UPDATE_ACCESS, customException.getErrorCode()); // 에러 코드 검증
        System.out.println("에러 코드: " + customException.getErrorCode()); // 에러 코드 로그로 출력

        verify(recordRepository, times(1)).findById(recordId);
        verify(recordRepository, never()).save(any(Record.class));
        verify(recordTagService, never()).delete(recordId);
        verify(recordTagService, never()).save(anyLong(), any(Record.class), anyString());

    }


}
