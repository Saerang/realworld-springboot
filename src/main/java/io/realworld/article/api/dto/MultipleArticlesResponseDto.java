package io.realworld.article.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MultipleArticlesResponseDto {
    private final List<ArticleResponseDto> articles;

    @Builder
    public MultipleArticlesResponseDto(List<ArticleResponseDto> articles) {
        this.articles = articles;
    }
}
