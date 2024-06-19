package com.example.myrok.dto.pagination;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
//특정한 Dto타입을 사용할 수 있도로 제너릭을 사용한다
public class PageResponseDto<E> {
    //페이징 결과물
    //-실제 dto
    private List<E> dtoList;

    private List<Integer> pageNumberList;

   private PageRequestDto pageRequestDto;

   private boolean prev, next;

   private int totalCount, prevPage, nextPage, totalPage, current;

   @Builder(builderMethodName = "withAll")
    public PageResponseDto(List<E> dtoList, PageRequestDto pageRequestDto, long total){
        this.dtoList = dtoList;
        this.pageRequestDto = pageRequestDto;
        //계산
        this.totalCount = (int) total;

        //끝페이지 end
        int end = (int)(Math.ceil(pageRequestDto.getPage() / 10.0)) * 10;

        int start = end - 9;

        //진짜 마지막 페이지
        int last = (int)(Math.ceil(totalCount/(double)pageRequestDto.getSize()));

        end = end > last?last:end;

        this.prev = start > 1;
        this.next = totalCount > end * pageRequestDto.getSize();

        this.pageNumberList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());

        this.prevPage = prev ? start -1 :0;
        this.nextPage = next ? end + 1:0;
    }
}


