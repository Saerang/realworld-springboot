package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.ArticleUpdateDto;
import io.realworld.article.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

    Article createArticle(ArticleCreateDto articleCreateDto, Long userId);

    Article getArticle(String slug);

    Page<Article> getArticles(String tag, String author, String favorited, Pageable pageable);

    Article updateArticle(ArticleUpdateDto dto, String slug, Long userId);

    void deleteArticle(String slug, Long userId);

    Page<Article> getArticlesByArticleIds(List<Long> articleIds, Pageable pageable);

    Page<Article> getArticlesByUserIds(List<Long> userIds, Pageable pageable);
}
