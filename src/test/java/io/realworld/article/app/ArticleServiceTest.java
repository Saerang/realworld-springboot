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
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        ArticleCreateDto dto = ArticleCreateDto.builder()
                .title("title")
                .description("description")
                .tags(Set.of(tagRequestDto))
                .body("body").build();

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
    @Disabled
    @Description("Query Dsl 적용 후 작성 필요.")
    void getAllArticles_ByTag() {
        //given
        Tag tag = saveTag();
        Article article1 = saveArticle("title1", "body1", tag, "description");
        Article article2 = saveArticle("title2", "body2", tag, "description");

        //when
        Page<Article> articles = articleService.getArticles(tag.getTag(), null, null, PageRequest.of(0, 20));

        //then
        assertThat(articles).hasSize(2);
        assertThat(articles).extracting("title").contains(article1.getTitle(), article2.getTitle());
        assertThat(articles).extracting("body").contains(article1.getBody(), article2.getBody());
        assertThat(articles).extracting("slug").contains(article1.getSlug(), article2.getSlug());
    }

    @Test
    @Disabled
    @Description("Query DSL 적용 후 다시 작업")
    void getPageArticles() {
        //given
        Tag tag = saveTag();
        saveArticle("title1", "body1", tag, "description");
        Article article2 = saveArticle("title2", "body2", tag, "description");

        //when
        Page<Article> articles = articleService.getArticles(tag.getTag(), null, null, PageRequest.of(0, 1));

        //then
        assertThat(articles).hasSize(1);
        assertThat(articles).extracting("title").contains(article2.getTitle());
        assertThat(articles).extracting("body").contains(article2.getBody());
        assertThat(articles).extracting("slug").contains(article2.getSlug());
    }

    @Test
    void getArticles_byArticleIds() {
        //given
        List<Long> articleIds = List.of(101L, 102L, 103L);
        PageRequest pageRequest = PageRequest.of(0, 10);

        //when
        Page<Article> articles = articleService.getArticlesByArticleIds(articleIds, pageRequest);

        //then
        assertThat(articles).hasSize(3);
        assertThat(articles).extracting("id").isEqualTo(articleIds);
    }

    @Test
    void updateArticle() {
        //given
        Article savedArticle = saveArticleWithTag();
        String slug = savedArticle.getSlug();

        ArticleUpdateDto dto = ArticleUpdateDto.builder()
                .title("title1")
                .body("body1")
                .description("description1")
                .build();

        //when
        Article updatedArticle = articleService.updateArticle(dto, slug, savedArticle.getUserId());
        em.flush();
        em.clear();

        //then
        assertThat(updatedArticle.getTitle()).isEqualTo(dto.getTitle());
        assertThat(updatedArticle.getBody()).isEqualTo(dto.getBody());
        assertThat(updatedArticle.getDescription()).isEqualTo(dto.getDescription());
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
        return saveArticle("title", "body", tag, "description");
    }

    private Tag saveTag() {
        return tagRepository.save(Tag.builder().tag("tag").build());
    }

    private Article saveArticle(String title, String body, Tag tag, String description) {
        Article article = Article.builder()
                .userId(101L)
                .title(title)
                .description(description)
                .body(body)
                .build();

        article.addTags(Set.of(tag));

        return articleRepository.save(article);
    }

}
