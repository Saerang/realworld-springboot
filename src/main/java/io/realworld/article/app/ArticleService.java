package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;

public interface ArticleService {
    SingleArticleResponseDto createArticle(ArticleCreateDto article);
}
