package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.ArticleUpdateDto;
import io.realworld.article.api.dto.MultipleArticlesResponseDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;
import org.springframework.data.domain.Pageable;

public interface ArticleMapperService {

    SingleArticleResponseDto getArticle(String slug, Long userId);

    MultipleArticlesResponseDto getArticles(String tag, String author, String favorited, Long userId, Pageable pageable);

    MultipleArticlesResponseDto getFeedArticles(Long currentUserId, Pageable pageable);

    SingleArticleResponseDto createArticle(ArticleCreateDto articleCreateDto, Long currentUserId);

    void deleteArticle(String slug, Long currentUserId);

    SingleArticleResponseDto updateArticle(ArticleUpdateDto dto, String slug, Long currentUserId);
}
