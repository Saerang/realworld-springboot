package io.realworld.article.api.dto;

import io.realworld.article.domain.Tag;
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
