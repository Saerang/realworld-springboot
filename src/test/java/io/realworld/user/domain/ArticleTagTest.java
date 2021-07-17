package io.realworld.user.domain;

import io.realworld.article.domain.Article;
import io.realworld.article.domain.ArticleTag;
import io.realworld.article.domain.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleTagTest {

    @Test
    void createArticleTag() {
        // given
        Tag tag = Tag.builder().tag("tag").build();
        Article article = Article.builder().body("body").build();

        // when
        ArticleTag articleTag = ArticleTag.builder()
                .tag(tag)
                .article(article)
                .build();

        // then
        assertThat(articleTag.getTag().getTag()).isEqualTo("tag");
        assertThat(articleTag.getArticle().getBody()).isEqualTo("body");
    }
}
