package com.example.myrok.repository;

import com.example.myrok.domain.Project;
import com.example.myrok.domain.Record;
import com.example.myrok.dto.pagination.PageRequestDto;
import com.example.myrok.repository.search.RecordSearch;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Log4j2
public class RecordRepositoryTests {

    @Autowired
    RecordRepository recordRepository;


    @Autowired
    ProjectRepository projectRepository;
//    @Test
//    public void findRecordByProjectId(){
//        List<Record> recordList = recordRepository.findAllByProjectId(1L);
//        System.out.println(Arrays.asList(recordList.toString()));
//    }
    @Test
    public void findProject(){
        Project project = projectRepository.findById(4L).orElseThrow();
        System.out.println(project.getProjectName());
    }

    @Test
    public void findRecordByTagAndValue(){
        PageRequestDto pageRequestDto = PageRequestDto.builder().build();
        recordRepository.search(pageRequestDto, "뉴", "제주도", 1L);
    }
}
