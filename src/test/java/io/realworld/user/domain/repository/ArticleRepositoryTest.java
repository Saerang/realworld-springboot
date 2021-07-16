package io.realworld.user.domain.repository;

import io.realworld.article.domain.Article;
import io.realworld.article.domain.ArticleTag;
import io.realworld.article.domain.Tag;
import io.realworld.user.domain.FollowRelation;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Set;

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
                .slug("slug")
                .body("body")
                .description("description")
                .favorited(true)
                .favoritesCount(1)
                .title("title")
                .userId(1L)
                .build();

//        Tag tag = Tag.builder().tag("tag").build();
//        em.persist(tag);
//
//        ArticleTag articleTag = ArticleTag.builder()
//                .article(article)
//                .tag(tag)
//                .build();
//        em.persist(articleTag);

        //when
        articleRepository.save(article);
        em.flush();
        em.clear();

        //then
        Article findArticle = articleRepository.findByUserId(1L).orElse(Article.builder().build());

        assertThat(findArticle.getSlug()).isEqualTo(article.getSlug());
        assertThat(findArticle.getBody()).isEqualTo(article.getBody());
        assertThat(findArticle.getUserId()).isEqualTo(article.getUserId());
    }

}
