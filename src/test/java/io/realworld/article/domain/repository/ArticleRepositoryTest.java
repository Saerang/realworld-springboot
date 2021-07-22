package io.realworld.article.domain.repository;

import io.realworld.article.domain.Article;
import io.realworld.common.exception.ArticleNotFoundException;
import io.realworld.tag.domain.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArticleRepositoryTest {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    TestEntityManager em;

    @Test
    void persistence() {
        //given
        Article article = Article.builder()
                .body("body")
                .description("description")
                .title("title")
                .userId(101L)
                .build();

        //when
        Article savedArticle = articleRepository.save(article);
        em.flush();
        em.clear();

        //then
        Article findArticle = articleRepository.findBySlug(savedArticle.getSlug()).orElseThrow(() -> new ArticleNotFoundException(savedArticle.getId()));

        assertThat(findArticle.getSlug()).isEqualTo(article.getSlug());
        assertThat(findArticle.getBody()).isEqualTo(article.getBody());
        assertThat(findArticle.getUserId()).isEqualTo(article.getUserId());
    }

}
