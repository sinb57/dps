package com.project.dps.mapstruct.dto;

import lombok.Getter;

@Getter
public class CheckItemDto {

    private String content;

    // 생성자 메서드
    public CheckItemDto(String content) {
        this.content = content;
    }
}