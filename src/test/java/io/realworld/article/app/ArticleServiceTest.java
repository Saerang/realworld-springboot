package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.ArticleUpdateDto;
import io.realworld.article.domain.Article;
import io.realworld.article.domain.repository.ArticleRepository;
import io.realworld.common.WithDefaultUser;
import io.realworld.common.exception.ArticleNotFoundException;
import io.realworld.tag.app.dto.TagRequestDto;
import io.realworld.tag.domain.Tag;
import io.realworld.tag.domain.repository.TagRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.Set;

import static io.realworld.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ArticleServiceTest {

    @Autowired
    ArticleService articleService;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    EntityManager em;

    @Test
    @WithDefaultUser
    void createArticle() {
        // given
        TagRequestDto tagRequestDto = TagRequestDto.builder().tag("tag").build();
        ArticleCreateDto.ArticleDto articleDto = ArticleCreateDto.ArticleDto.builder()
                .title("title")
                .description("description")
                .tags(Set.of(tagRequestDto))
                .body("body")
                .build();

        ArticleCreateDto dto = ArticleCreateDto.builder()
                .articleDto(articleDto)
                .build();

        // when
        Article savedArticle = articleService.createArticle(dto, 101L);

        em.flush();
        em.clear();
        Article findArticle = articleRepository.findBySlug(savedArticle.getSlug()).orElseThrow(() -> new ArticleNotFoundException(savedArticle.getSlug()));

        // then
        assertThat(savedArticle.getTitle()).isEqualTo(findArticle.getTitle());
        assertThat(savedArticle.getDescription()).isEqualTo(findArticle.getDescription());
        assertThat(savedArticle.getBody()).isEqualTo(findArticle.getBody());
    }

    @Test
    void getArticle() {
        //given
        Article savedArticle = saveArticleWithTag();

        //when
        Article article = articleService.getArticle(savedArticle.getSlug());

        //then
        assertThat(article.getId()).isEqualTo(savedArticle.getId());
        assertThat(article.getBody()).isEqualTo(savedArticle.getBody());
        assertThat(article.getTitle()).isEqualTo(savedArticle.getTitle());
    }

    @Test
    void getArticles_ByTag() {
        //given
        Tag tag = saveTag();
        Article article1 = saveArticle("title1", "body1", tag, defaultUser().getId());
        Article article2 = saveArticle("title2", "body2", tag, defaultUser().getId());

        //when
        Page<Article> articles = articleService.getArticles(tag.getTag(), null, null, PageRequest.of(0, 20));

        //then
        assertThat(articles).hasSize(2);
        assertThat(articles).extracting("title").contains(article1.getTitle(), article2.getTitle());
        assertThat(articles).extracting("body").contains(article1.getBody(), article2.getBody());
        assertThat(articles).extracting("slug").contains(article1.getSlug(), article2.getSlug());
    }

    @Test
    void getArticles_ByAuthor() {
        //given
        Tag tag = saveTag();
        Article article1 = saveArticle("title1", "body1", tag, 203L);
        Article article2 = saveArticle("title2", "body2", tag, 203L);

        //when
        Page<Article> articles = articleService.getArticles(null, "realworld203", null, PageRequest.of(0, 20));

        //then
        assertThat(articles).hasSize(2);
        assertThat(articles).extracting("title").contains(article1.getTitle(), article2.getTitle());
        assertThat(articles).extracting("body").contains(article1.getBody(), article2.getBody());
        assertThat(articles).extracting("slug").contains(article1.getSlug(), article2.getSlug());
    }

    @Test
    void getArticles_ByFavorited() {
        //given
        //when
        Page<Article> articles = articleService.getArticles(null, null, "realworld101", PageRequest.of(0, 5));

        //then
        assertThat(articles).hasSize(4);
        assertThat(articles).extracting("title").contains("title101", "title102", "title103", "title104");
        assertThat(articles).extracting("body").contains("body101", "body102", "body103", "body104");
        assertThat(articles).extracting("slug").contains("slug101", "slug102", "slug103", "slug104");
    }


    @Test
    void updateArticle() {
        //given
        Article savedArticle = saveArticleWithTag();
        String slug = savedArticle.getSlug();

        ArticleUpdateDto.ArticleDto articleDto = ArticleUpdateDto.ArticleDto.builder()
                .title("title1")
                .body("body1")
                .description("description1")
                .build();
        ArticleUpdateDto dto = ArticleUpdateDto.builder()
                .articleDto(articleDto)
                .build();

        //when
        Article updatedArticle = articleService.updateArticle(dto, slug, savedArticle.getUserId());
        em.flush();
        em.clear();

        //then
        assertThat(updatedArticle.getTitle()).isEqualTo(dto.getArticleDto().getTitle());
        assertThat(updatedArticle.getBody()).isEqualTo(dto.getArticleDto().getBody());
        assertThat(updatedArticle.getDescription()).isEqualTo(dto.getArticleDto().getDescription());
        assertThat(updatedArticle.getSlug()).isNotEqualTo(slug);
    }

    @Test
    void deleteArticle() {
        //given
        Article savedArticle = saveArticleWithTag();
        Optional<Article> beforeArticle = articleRepository.findBySlug(savedArticle.getSlug());
        assertThat(beforeArticle).isNotEmpty();

        //when
        articleService.deleteArticle(savedArticle.getSlug(), savedArticle.getUserId());

        em.flush();
        em.clear();

        Optional<Article> afterArticle = articleRepository.findBySlug(savedArticle.getSlug());

        //then
        assertThat(afterArticle).isEmpty();
    }

    private Article saveArticleWithTag() {
        Tag tag = saveTag();
        return saveArticle("title", "body", tag, defaultUser().getId());
    }

    private Tag saveTag() {
        return tagRepository.save(Tag.builder().tag("tag").build());
    }

    private Article saveArticle(String title, String body, Tag tag, Long userId) {
        Article article = Article.builder()
                .userId(userId)
                .title(title)
                .description("description")
                .body(body)
                .build();

        article.addTags(Set.of(tag));

        return articleRepository.save(article);
    }

}
