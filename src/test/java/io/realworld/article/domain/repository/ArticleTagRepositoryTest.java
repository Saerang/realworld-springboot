package io.realworld.article.domain.repository;

import io.realworld.article.domain.Article;
import io.realworld.article.domain.ArticleTag;
import io.realworld.tag.domain.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArticleTagRepositoryTest {
    
    @Autowired
    ArticleTagRepository articleTagRepository;
    @Autowired
    TestEntityManager em;

    @Test
    void persistence() throws IllegalAccessException {
        // given
        Article article = Article.builder()
                .body("body")
                .description("description")
                .title("title")
                .userId(101L)
                .build();
        em.persist(article);

        Tag tag = Tag.builder().tag("tag").build();
        em.persist(tag);

        // when
        ArticleTag articleTag = ArticleTag.builder()
                .article(article)
                .tagId(tag.getId())
                .build();
        ArticleTag saveArticleTag = articleTagRepository.save(articleTag);

        em.flush();
        em.clear();

        ArticleTag findArticleTag = articleTagRepository.findById(saveArticleTag.getId()).orElseThrow(IllegalAccessException::new);

        // then
        assertThat(findArticleTag.getArticle().getUserId()).isEqualTo(101);
        assertThat(findArticleTag.getArticle().getBody()).isEqualTo("body");
        assertThat(findArticleTag.getTagId()).isEqualTo(tag.getId());
    }
}
