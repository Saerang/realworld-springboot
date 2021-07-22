package io.realworld.article.app;

import io.realworld.article.api.dto.MultipleArticleSearchDto;
import io.realworld.article.api.dto.MultipleArticlesResponseDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;
import org.springframework.data.domain.Pageable;

public interface ArticleMapperService {
    SingleArticleResponseDto getSingleArticleResponseDto(String slug, long userId);

    MultipleArticlesResponseDto getMultipleArticlesResponseDto(MultipleArticleSearchDto dto, long userId);

    MultipleArticlesResponseDto getMultipleArticlesResponseDtoFeed(Pageable pageable, long userId);
}
