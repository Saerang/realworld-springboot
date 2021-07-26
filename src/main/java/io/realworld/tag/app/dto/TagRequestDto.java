package io.realworld.tag.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class TagRequestDto {
    private String tag;

    @Builder
    public TagRequestDto(String tag) {
        this.tag = tag;
    }
}
