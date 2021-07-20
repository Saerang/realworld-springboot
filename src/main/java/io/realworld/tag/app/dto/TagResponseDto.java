package io.realworld.tag.app.dto;

import io.realworld.tag.domain.Tag;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TagResponseDto {
    private final String tag;

    @Builder
    public TagResponseDto(String tag) {
        this.tag = tag;
    }

    public static Tag toEntity(TagResponseDto dto) {
        return Tag.builder()
                .tag(dto.getTag())
                .build();
    }
}
