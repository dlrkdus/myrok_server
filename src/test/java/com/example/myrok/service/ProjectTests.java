package com.example.myrok.service;

import com.example.myrok.dto.record.RecordClass;
import com.example.myrok.dto.pagination.PageRequestDto;
import com.example.myrok.dto.pagination.PageResponseDto;
import com.example.myrok.dto.record.RecordDTO;
import com.example.myrok.repository.ProjectRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Log4j2
public class ProjectTests {
    @Autowired
    ProjectService projectService;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    RecordService recordService;

//    @Test
//    public void testRegister(){
//        ProjectDTO.RegisterProject projectDto = ProjectDTO.RegisterProject.builder()
//                .projectName("test name 1")
//                .startDate("")
//                .endDate("")
//                .build();
//        Long projectId = projectService.register(projectDto, 1L);
//    }

//    @Test
//    public void testGetMembersByProjectId(){
//        ProjectDTO.ProjectMembersDto projectMembersDto = projectService.getProjectMembers(4L);
//        log.info(projectMembersDto);
//    }

    @Test
    public void getRecordListTests(){
        List<RecordClass.RecordListObject> listObjects = recordService.getRecords(1L);
        System.out.println(Arrays.asList(listObjects));
    }

    @Test
    public void getRecordListPaginationTests(){
        PageRequestDto pageRequestDto = PageRequestDto.builder().build();
        PageResponseDto<RecordClass.RecordListObject> listObjects = recordService.getRecords(pageRequestDto, 1L);
        System.out.println(Arrays.asList(listObjects));
    }

    @Test
    public void findRecordByTagAndValue(){
        PageRequestDto pageRequestDto = PageRequestDto.builder().build();
        recordService.getRecordsBySearch(pageRequestDto,  "보험","의대증원", 1L);
    }

    @Test
    public void saveTest(){
        recordService.save(RecordDTO.builder()
                        .memberList(Arrays.asList(2L, 3L))
                        .projectId(2L)
                        .recordWriterId(2L)
                        .recordName("테스트")
                        .recordContent("<h2>기획 고도화 브리핑</h2><ul><li><p>송지은</p><p><a href=\"https://prod-files-secure.s3.us-west-2.amazonaws.com/1518a9da-0e39-4734-808f-35897e20811c/af5d92e9-a11b-4011-812f-340073c10fd1/이름_없는_노트북_(1).pdf\">송지은</a></p><ul><li>파일 서버 용량 관리, 보안 필요</li><li>회의록 동시 편집 [채팅보다 동시 편집] → 혹은 팀장만 편집 가능 권한 부여<ul><li>개인별 수정 혹은 동시에 에디터 사용</li></ul></li><li>친구 추가, 프로필 등 공수가 많이 듬</li><li>여러 팀 참여 → 나중에 작업<ul><li>여러 플젝끼리 작업시 통신 작업 시간 오래 걸림</li></ul></li></ul></li><li>이가연<ul><li>회원가입/로그인..</li><li>실시간 채팅</li><li>알림<ul><li>채팅알림, 회의시작 알림</li><li>웹소켓</li></ul></li><li>팀구성 → 초대코드</li><li>부가기능<ul><li>캘린더<ul><li>프론트 : 라이브러리 사용 가능</li></ul></li><li>에디터 공동작업기능<ul><li>에디터 백엔드</li></ul></li><li>대시보드<ul><li>버스형 → 대시보드로 변경</li></ul></li></ul></li></ul></li></ul><p> </p><ul><li><p>백승지</p><p><a href=\"https://www.figma.com/embed?embed_host=notion&amp;url=https%3A%2F%2Fwww.figma.com%2Ffile%2FaitHtCy4GLaAlA1KzaIVHi%2FUntitled%3Ftype%3Dwhiteboard%26node-id%3D19-2020%26t%3DTxdg3BvcYyw94p3J-0\">https://www.figma.com/embed?embed_host=notion&amp;url=https%3A%2F%2Fwww.figma.com%2Ffile%2FaitHtCy4GLaAlA1KzaIVHi%2FUntitled%3Ftype%3Dwhiteboard%26node-id%3D19-2020%26t%3DTxdg3BvcYyw94p3J-0</a></p><ul><li>버스형 재고려 필요</li></ul></li><li>김도연<ul><li>관련 사례 분석<ul><li>협업툴_플로우</li><li>공동작업 기능 없음</li></ul></li></ul></li></ul><h2>메인 기능 (서비스의 정체성)</h2><ul><li>회의록 관리</li></ul><h2>필수 기능</h2><p> </p><ul><li>팀 생성<ul><li>팀 참가 → 초대코드</li></ul></li><li><p>로그인/회원가입</p><p>소셜로그인</p></li><li>회의록<ul><li>회의록 작성 (수정)<ul><li>동시작업(CRDT) (작성 완료 이후에도 실시간 CRUD 할거?)</li><li>빠른 동기화에는 Redis가 좋다고 함</li></ul></li><li>회의록 삭제</li><li>회의록 조회<ul><li>회의록 관련 질문 AI 챗봇 (플로우 형식)</li><li>첨부파일</li></ul></li></ul></li><li>회의록 목록 조회<ul><li>기간 조회</li><li>태그별 조회</li></ul></li><li>메인화면 (대시보드)<ul><li>태그<ul><li>원형그래프(퍼센트)</li><li>태그 순위 (목록)</li></ul></li><li>회의록 목록</li></ul></li></ul><h2>부가 기능</h2><ul><li>알림서비스 (음성 채팅)</li></ul><p> </p><p>이런식으로 구현하고 싶었음 (채팅 대신 음성채팅)</p><ul><li>프로젝트 전환</li><li>회의 안에서 논의해야하는 안건에 대해 체크박스 제공 뒤 미완료 건에 대해 자동으로 다음 회의록안에 작성시킴</li></ul>")
                        .recordDate(LocalDate.now())
                        .tagList(Arrays.asList("tag1"))

                .build());
    }
}
