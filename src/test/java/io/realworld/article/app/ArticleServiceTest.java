package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.MultipleArticleSearchDto;
import io.realworld.article.api.dto.SingleArticleSearchDto;
import io.realworld.article.domain.Article;
import io.realworld.article.domain.repository.ArticleRepository;
import io.realworld.common.WithDefaultUser;
import io.realworld.common.exception.ArticleNotFound;
import io.realworld.tag.app.dto.TagRequestDto;
import io.realworld.tag.domain.Tag;
import io.realworld.tag.domain.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
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
    EntityManager em;
    @Autowired
    ArticleRepository articleRepository;

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
        Article savedArticle = articleService.createArticle(dto, 1);

        // TODO : DB 확인은 어떤 값으로 할지 고민
        em.flush();
        em.clear();
        Article findArticle = articleRepository.findById(savedArticle.getId()).orElseThrow(() -> new ArticleNotFound(savedArticle.getId()));

        // then
        assertThat(savedArticle.getTitle()).isEqualTo(findArticle.getTitle());
        assertThat(savedArticle.getDescription()).isEqualTo(findArticle.getDescription());
        assertThat(savedArticle.getBody()).isEqualTo(findArticle.getBody());
    }

    @Test
    void getArticle() {
        //given
        Tag tag = tagRepository.save(Tag.builder().tag("tag").build());
        em.flush();
        em.clear();

        Article savedArticle = saveArticle("slug", "title", "body", tag);
        SingleArticleSearchDto dto = SingleArticleSearchDto.builder().slug(savedArticle.getSlug()).build();

        //when
        Article article = articleService.getArticle(dto);

        //then
        assertThat(article.getId()).isEqualTo(savedArticle.getId());
        assertThat(article.getBody()).isEqualTo(savedArticle.getBody());
        assertThat(article.getTitle()).isEqualTo(savedArticle.getTitle());
    }

    @Test
    void getArticles_ByTag() {
        //given
        // TODO: 데이터 저장하는 부분 Tag 관련 영속성 문제 걸림. 일단 디비로 저장. 추후 확인필요.
        Tag tag = tagRepository.save(Tag.builder().tag("tag").build());
        em.flush();
        em.clear();

        Article article1 = saveArticle("slug1", "title1", "body1", tag);
        Article article2 = saveArticle("slug2", "title2", "body2", tag);
        MultipleArticleSearchDto dto = MultipleArticleSearchDto.builder()
                .tag("tag")
                .build();

        //when
        List<Article> articles = articleService.getArticles(dto, 2);

        //then
        assertThat(articles).hasSize(2);
        assertThat(articles).extracting("title").contains(article1.getTitle(), article2.getTitle());
        assertThat(articles).extracting("body").contains(article1.getBody(), article2.getBody());
        assertThat(articles).extracting("slug").contains(article1.getSlug(), article2.getSlug());
    }

    private Article saveArticle(String slug, String title, String body, Tag tag) {
        Article article = Article.builder()
                .userId(1L)
                .title(title)
                .description("description")
                .body(body)
                .favorited(false)
                .favoritesCount(1)
                .slug(slug)
                .build();

        article.addTags(Set.of(tag));

        return articleRepository.save(article);
    }

}
