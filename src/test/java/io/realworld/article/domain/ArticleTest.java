package io.realworld.article.domain;

import io.realworld.Fixtures;
import org.junit.jupiter.api.Test;

import static io.realworld.Fixtures.*;
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

    @Test
    void updateArticle() {
        // given
        Article article = anArticle().build();
        String title = "updateTitle";
        String body = "updateBody";
        String description = "updateDescription";

        // when
        article.updateArticle(title, body, description);

        // then
        assertThat(article.getTitle()).isEqualTo(title);
        assertThat(article.getBody()).isEqualTo(body);
        assertThat(article.getDescription()).isEqualTo(description);
    }

    @Test
    void updateArticle_no_title() {
        // given
        Article article = anArticle().build();
        String title = "updateTitle";
        String body = "updateBody";
        String description = "updateDescription";

        // when
        article.updateArticle(title, body, description);

        // then
        assertThat(article.getTitle()).isEqualTo(title);
        assertThat(article.getBody()).isEqualTo(body);
        assertThat(article.getDescription()).isEqualTo(description);
    }
}
