package io.realworld.tag.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@ToString
public class TagResponseDtos {
    private final Set<String> tags;

    @Builder
    public TagResponseDtos(Set<String> tags) {
        this.tags = tags;
    }
}
