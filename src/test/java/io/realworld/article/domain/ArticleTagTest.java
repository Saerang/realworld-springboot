package io.realworld.article.domain;

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
