package com.example.myrok.dto.project;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TagCountDTO{
    private String tagName;
    private Long count;
}