package io.realworld.article.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class MultipleArticlesResponseDto {

    private final List<ArticleResponseDto> articles;

    private final long articlesCount;

    @Builder
    public MultipleArticlesResponseDto(List<ArticleResponseDto> articles, long articlesCount) {
        this.articles = articles;
        this.articlesCount = articlesCount;
    }
}
