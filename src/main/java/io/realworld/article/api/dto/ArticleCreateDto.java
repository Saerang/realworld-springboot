package io.realworld.article.api.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
public class ArticleCreateDto {
    @NotNull
    private final String title;
    @NotNull
    private final String description;
    @NotNull
    private final String body;
    private final Set<TagResponseDto> tags;

    @Builder
    public ArticleCreateDto(String title, String description, String body, Set<TagResponseDto> tags) {
        this.title = title;
        this.description = description;
        this.body = body;
        this.tags = tags;
    }
}
