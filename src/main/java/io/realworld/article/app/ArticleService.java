package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.ArticleResponseDto;
import io.realworld.article.domain.Article;

public interface ArticleService {
    ArticleResponseDto createArticle(ArticleCreateDto article);
}
