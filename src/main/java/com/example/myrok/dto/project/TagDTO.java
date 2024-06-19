package com.example.myrok.dto.project;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TagDTO{
    private String tagName;
    private Long percentage;
}