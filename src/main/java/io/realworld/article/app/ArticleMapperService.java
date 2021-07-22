package io.realworld.article.app;

import io.realworld.article.api.dto.MultipleArticleSearchDto;
import io.realworld.article.api.dto.MultipleArticlesResponseDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;

public interface ArticleMapperService {
    SingleArticleResponseDto getSingleArticleResponseDto(String slug, long userId);

    MultipleArticlesResponseDto getMultipleArticleResponseDto(MultipleArticleSearchDto dto, long userId);
}
