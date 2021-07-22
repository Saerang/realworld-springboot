package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.ArticleUpdateDto;
import io.realworld.article.api.dto.MultipleArticleSearchDto;
import io.realworld.article.domain.Article;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {

    Article createArticle(ArticleCreateDto articleCreateDto, long userId);

    Article getArticle(String slug);

    Page<Article> getArticlesFromSearchDto(MultipleArticleSearchDto articleSearchDto, long userId);

    Article updateArticle(ArticleUpdateDto dto, String slug);

    void deleteArticle(String slug);

    List<Article> getFeedArticles(List<Long> articleIds);
}
