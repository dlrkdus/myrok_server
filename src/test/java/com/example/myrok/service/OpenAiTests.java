package com.example.myrok.service;

import com.example.myrok.dto.classtype.ClovaDTO;
import com.example.myrok.service.openapi.ClovaSummary;
import com.example.myrok.service.openapi.OpenAi;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OpenAiTests {

    @Autowired
    OpenAi openAi;
    @Autowired
    ClovaSummary clovaSummary;

    @Test
    public void openAitest() throws JsonProcessingException, JSONException {
        openAi.makeRequest();
        openAi.makeThread();
        openAi.makeMessage();
        openAi.makeThreadRun();
        openAi.getMessage();
    }

    @Test
    public void clovaTest() throws JsonProcessingException, JSONException {
        ClovaDTO.RequestDto requestDto = ClovaDTO.RequestDto.builder()
                .title("'하루 2000억' 판 커지는 간편송금 시장")
                .content("간편송금 이용금액이 하루 평균 2000억원을 넘어섰다. 한국은행이 17일 발표한 '2019년 상반기중 전자지급서비스 이용 현황'에 따르면 올해 상반기 간편송금서비스 이용금액(일평균)은 지난해 하반기 대비 60.7% 증가한 2005억원으로 집계됐다. 같은 기간 이용건수(일평균)는 34.8% 늘어난 218만건이었다. 간편 송금 시장에는 선불전자지급서비스를 제공하는 전자금융업자와 금융기관 등이 참여하고 있다. 이용금액은 전자금융업자가 하루평균 1879억원, 금융기관이 126억원이었다. 한은은 카카오페이, 토스 등 간편송금 서비스를 제공하는 업체 간 경쟁이 심화되면서 이용규모가 크게 확대됐다고 분석했다. 국회 정무위원회 소속 바른미래당 유의동 의원에 따르면 카카오페이, 토스 등 선불전자지급서비스 제공업체는 지난해 마케팅 비용으로 1000억원 이상을 지출했다. 마케팅 비용 지출규모는 카카오페이가 491억원, 비바리퍼블리카(토스)가 134억원 등 순으로 많았다.")
                .build();
        ClovaDTO.ResponseDto responseDto = clovaSummary.get(requestDto);
        System.out.println(responseDto);
    }
}
