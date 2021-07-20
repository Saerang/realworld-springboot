package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.ArticleResponseDto;

public interface ArticleMapperService {
    ArticleResponseDto getArticle(ArticleCreateDto dto);
}
