package io.realworld.article.app;

import io.realworld.article.domain.ArticleTag;

import java.util.List;

public interface ArticleTagService {
    List<ArticleTag> getArticleTags(List<Long> articleIds);
}
