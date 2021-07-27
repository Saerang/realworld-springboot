package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.ArticleUpdateDto;
import io.realworld.article.api.dto.MultipleArticlesResponseDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;
import org.springframework.data.domain.Pageable;

public interface ArticleMapperService {
    SingleArticleResponseDto getSingleArticleResponseDto(String slug, Long userId);

    MultipleArticlesResponseDto getArticles(String tag, String author, String favorited, Pageable pageable, Long userId);

    MultipleArticlesResponseDto getFeedArticles(Pageable pageable, Long userId);

    SingleArticleResponseDto createArticle(ArticleCreateDto articleCreateDto, Long currentUserId);

    void deleteArticle(String slug, Long userId);

    SingleArticleResponseDto updateArticle(ArticleUpdateDto dto, String slug, Long userId);
}
