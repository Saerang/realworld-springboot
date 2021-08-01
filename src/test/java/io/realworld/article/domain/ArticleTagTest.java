package io.realworld.article.domain;

import io.realworld.tag.domain.Tag;
import org.junit.jupiter.api.Test;

import static io.realworld.Fixtures.aTag;
import static io.realworld.Fixtures.anArticle;
import static org.assertj.core.api.Assertions.assertThat;

public class ArticleTagTest {

    @Test
    void createArticleTag() {
        // given
        Article article = anArticle().build();
        Tag tag = aTag().build();

        // when
        ArticleTag articleTag = ArticleTag.builder()
                .tagId(tag.getId())
                .article(article)
                .build();

        // then
        assertThat(articleTag.getTagId()).isEqualTo(tag.getId());
        assertThat(articleTag.getArticle().getBody()).isEqualTo(article.getBody());
    }
}
