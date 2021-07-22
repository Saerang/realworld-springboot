package io.realworld.article.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleTest {

    @Test
    void createArticle() {
        //given
        //when
        Article article = Article.builder()
                .title("title")
                .body("articleBody")
                .userId(1L)
                .build();

        //then
        assertThat(article.getTitle()).isEqualTo("title");
        assertThat(article.getBody()).isEqualTo("articleBody");
        assertThat(article.getSlug()).isNotBlank();
    }
}
