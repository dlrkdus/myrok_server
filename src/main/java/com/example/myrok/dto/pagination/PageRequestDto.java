package com.example.myrok.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder //속성이 늘어남 조회시 조건에 따라
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDto {

    @Builder.Default
    private int page = 1; //없으면 1의 값을 기본으로 가진다
    @Builder.Default
    private int size = 10;

}
