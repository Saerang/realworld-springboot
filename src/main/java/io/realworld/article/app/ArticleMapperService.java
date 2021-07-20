package io.realworld.article.app;

import io.realworld.article.api.dto.MultipleArticleSearchDto;
import io.realworld.article.api.dto.MultipleArticlesResponseDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;
import io.realworld.article.api.dto.SingleArticleSearchDto;

public interface ArticleMapperService {
    SingleArticleResponseDto getArticle(SingleArticleSearchDto dto, long userId);
    MultipleArticlesResponseDto getArticles(MultipleArticleSearchDto dto, long userId);
}
