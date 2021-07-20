package io.realworld.tag.app.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TagRequestDto {
    private String tag;

    @Builder
    public TagRequestDto(String tag) {
        this.tag = tag;
    }
}