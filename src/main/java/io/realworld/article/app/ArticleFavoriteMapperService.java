package io.realworld.article.app;

import io.realworld.article.api.dto.SingleArticleResponseDto;

public interface ArticleFavoriteMapperService {

    SingleArticleResponseDto favoriteArticle(String slug, Long userId);

    SingleArticleResponseDto unfavoriteArticle(String slug, Long userId);
}
