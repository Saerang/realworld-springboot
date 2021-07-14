package io.realworld.user.domain;

import io.realworld.article.domain.Article;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleTest {

    @Test
    void createArticle() {
        //given
        Article article = Article.builder()
                .body("articleBody")
                .build();
        //when

        //then
        assertThat(article.getBody()).isEqualTo("articleBody");
    }
}
