package io.realworld.article.api.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TagResponseDto {
    private final String tag;

    @Builder
    public TagResponseDto(String tag) {
        this.tag = tag;
    }
}
