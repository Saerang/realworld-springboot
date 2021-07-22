package io.realworld.article.api.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MultipleArticlesResponseDto {
    private final List<ArticleResponseDto> articles;
    private final long count;

    @Builder
    public MultipleArticlesResponseDto(List<ArticleResponseDto> articles, long count) {
        this.articles = articles;
        this.count = count;
    }
}
