package com.example.myrok.dto.record;

import com.example.myrok.domain.Project;
import com.example.myrok.domain.Record;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.commons.text.StringEscapeUtils;

import java.time.LocalDate;
import java.util.List;

public class RecordClass {

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class RecordListObject {
        Long recordId;
        String recordName;
        String recordDate;
        String recordWriterName;
    }

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class ResponseDTO {
       Long id;
       String summary;
    }
}
