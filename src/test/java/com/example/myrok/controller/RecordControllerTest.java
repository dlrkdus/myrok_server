//package com.example.myrok.controller;
//
//import com.example.myrok.controller.RecordController;
//import com.example.myrok.domain.Record;
//import com.example.myrok.dto.record.RecordDTO;
//import com.example.myrok.service.RecordService;
//import org.apache.commons.text.StringEscapeUtils;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ExtendWith(MockitoExtension.class)
//class RecordControllerTest {
//
//    @InjectMocks
//    private RecordController recordController;
//
//    @Mock
//    private RecordService recordService;
//
//    @Test
//    void RecordContentEscapeTest() {
//        // Given
//        String recordContent = "<h2>기획 고도화 브리핑</h2><ul><li><p>송지은</p><p><a href=\"https://prod-files-secure.s3.us-west-2.amazonaws.com/1518a9da-0e39-4734-808f-35897e20811c/af5d92e9-a11b-4011-812f-340073c10fd1/%E1%84%8B%E1%85%B5%E1%84%85%E1%85%B3%E1%86%B7_%E1%84%8B%E1%85%A5%E1%86%B9%E1%84%82%E1%85%B3%E1%86%AB_%E1%84%82%E1%85%A9%E1%84%90%E1%85%B3%E1%84%87%E1%85%AE%E1%86%A8_(1).pdf\">송지은</a></p><ul><li>파일 서버 용량 관리, 보안 필요</li><li>회의록 동시 편집 [채팅보다 동시 편집] → 혹은 팀장만 편집 가능 권한 부여<ul><li>개인별 수정 혹은 동시에 에디터 사용</li></ul></li><li>친구 추가, 프로필 등 공수가 많이 듬</li><li>여러 팀 참여 → 나중에 작업<ul><li>여러 플젝끼리 작업시 통신 작업 시간 오래 걸림</li></ul></li></ul></li><li>이가연<ul><li>회원가입/로그인..</li><li>실시간 채팅</li><li>알림<ul><li>채팅알림, 회의시작 알림</li><li>웹소켓</li></ul></li><li>팀구성 → 초대코드</li><li>부가기능<ul><li>캘린더<ul><li>프론트 : 라이브러리 사용 가능</li></ul></li><li>에디터 공동작업기능<ul><li>에디터 백엔드</li></ul></li><li>대시보드<ul><li>버스형 → 대시보드로 변경</li></ul></li></ul></li></ul></li></ul><p>&nbsp;</p><ul><li><p>백승지</p><p><a href=\"https://www.figma.com/embed?embed_host=notion&amp;url=https%3A%2F%2Fwww.figma.com%2Ffile%2FaitHtCy4GLaAlA1KzaIVHi%2FUntitled%3Ftype%3Dwhiteboard%26node-id%3D19-2020%26t%3DTxdg3BvcYyw94p3J-0\">https://www.figma.com/embed?embed_host=notion&amp;url=https%3A%2F%2Fwww.figma.com%2Ffile%2FaitHtCy4GLaAlA1KzaIVHi%2FUntitled%3Ftype%3Dwhiteboard%26node-id%3D19-2020%26t%3DTxdg3BvcYyw94p3J-0</a></p><ul><li>버스형 재고려 필요</li></ul></li><li>김도연<ul><li>관련 사례 분석<ul><li>협업툴_플로우</li><li>공동작업 기능 없음</li></ul></li></ul></li></ul><h2>메인 기능 (서비스의 정체성)</h2><ul><li>회의록 관리</li></ul><h2>필수 기능</h2><p>&nbsp;</p><ul><li>팀 생성<ul><li>팀 참가 → 초대코드</li></ul></li><li><p>로그인/회원가입</p><p>소셜로그인</p></li><li>회의록<ul><li>회의록 작성 (수정)<ul><li>동시작업(CRDT) (작성 완료 이후에도 실시간 CRUD 할거?)</li><li>빠른 동기화에는 Redis가 좋다고 함</li></ul></li><li>회의록 삭제</li><li>회의록 조회<ul><li>회의록 관련 질문 AI 챗봇 (플로우 형식)</li><li>첨부파일</li></ul></li></ul></li><li>회의록 목록 조회<ul><li>기간 조회</li><li>태그별 조회</li></ul></li><li>메인화면 (대시보드)<ul><li>태그<ul><li>원형그래프(퍼센트)</li><li>태그 순위 (목록)</li></ul></li><li>회의록 목록</li></ul></li></ul><h2>부가 기능</h2><ul><li>알림서비스 (음성 채팅)</li></ul><p>&nbsp;</p><p>이런식으로 구현하고 싶었음 (채팅 대신 음성채팅)</p><ul><li>프로젝트 전환</li><li>회의 안에서 논의해야하는 안건에 대해 체크박스 제공 뒤 미완료 건에 대해 자동으로 다음 회의록안에 작성시킴</li></ul>";
//        String escapedContent = StringEscapeUtils.escapeJson(recordContent);
//        RecordDTO recordDTO = new RecordDTO();
//        recordDTO.setRecordContent(recordContent);
//
//        Record savedRecord = new Record();
//        savedRecord.setId(1L);
//        savedRecord.setRecordContent(escapedContent);
//
//        Mockito.when(recordService.save(recordDTO)).thenReturn(savedRecord);
//
//        // When
//        ResponseEntity<Long> response = recordController.save(recordDTO);
//
//        // Then
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(Long.valueOf(1), response.getBody());
//        assertEquals(escapedContent, savedRecord.getRecordContent());
//    }
//}
