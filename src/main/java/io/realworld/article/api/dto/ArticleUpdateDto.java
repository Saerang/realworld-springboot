package io.realworld.article.api.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ArticleUpdateDto {
    private final String title;
    private final String description;
    private final String body;

    @Builder
    public ArticleUpdateDto(String title, String description, String body) {
        this.title = title;
        this.description = description;
        this.body = body;
    }
}
