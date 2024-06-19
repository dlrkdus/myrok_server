package com.example.myrok.dto.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
public record RecordUpdateDTO(
        @NotBlank(message = "제목은 필수 입력 사항입니다.")
        String recordName,
        @NotNull
        Long recordWriterId,
        List<String> tagList
) {
}
