package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.ArticleUpdateDto;
import io.realworld.article.api.dto.MultipleArticlesResponseDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;
import io.realworld.article.domain.Article;
import io.realworld.article.domain.repository.ArticleRepository;
import io.realworld.common.exception.ArticleNotFoundException;
import io.realworld.tag.app.dto.TagRequestDto;
import io.realworld.tag.domain.Tag;
import io.realworld.tag.domain.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ArticleMapperServiceTest {

    @Autowired
    ArticleMapperService articleMapperService;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    EntityManager em;

    @Test
    void getArticleResponseDto() {
        // given
        Tag tag = tagRepository.save(Tag.builder().tag("tag").build());
        em.flush();
        em.clear();

        Article savedArticle = saveArticle(tag);

        // when
        SingleArticleResponseDto result = articleMapperService.getArticle(savedArticle.getSlug(), 101L);

        // then
        assertThat(result.getArticle().getSlug()).isEqualTo(savedArticle.getSlug());
        assertThat(result.getArticle().getTitle()).isEqualTo(savedArticle.getTitle());
        assertThat(result.getArticle().getBody()).isEqualTo(savedArticle.getBody());
        assertThat(result.getArticle().isFavorited()).isFalse();
        assertThat(result.getArticle().getAuthor().getUsername()).isEqualTo("realworld101");
        assertThat(result.getArticle().getAuthor().getBio()).isEqualTo("bio101");
        assertThat(result.getArticle().getAuthor().getImage()).isEqualTo("image101");
    }

    @Test
    void getArticles() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 20);

        // when
        MultipleArticlesResponseDto multipleArticlesResponseDto = articleMapperService.getArticles(null, null, null, 101L, pageRequest);

        // then
        assertThat(multipleArticlesResponseDto.getArticlesCount()).isEqualTo(9);
    }

    @Test
    void getArticlesWrittenByAuthorsFollowed() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 20);

        // when
        MultipleArticlesResponseDto multipleArticlesResponseDtoFeed = articleMapperService.getFeedArticles(202L, pageRequest);

        // then
        assertThat(multipleArticlesResponseDtoFeed.getArticlesCount()).isEqualTo(3);
        assertThat(multipleArticlesResponseDtoFeed.getArticles()).extracting("title").containsExactly("title201", "title202", "title203");
        assertThat(multipleArticlesResponseDtoFeed.getArticles()).extracting("body").containsExactly("body201", "body202", "body203");
        assertThat(multipleArticlesResponseDtoFeed.getArticles()).extracting("author.following").containsOnly(true);
    }

    @Test
    void createArticle() {
        // given
        TagRequestDto tag1 = TagRequestDto.builder().tag("tag1").build();
        TagRequestDto tag2 = TagRequestDto.builder().tag("tag2").build();
        Long userId = 101L;

        ArticleCreateDto.ArticleDto articleDto = ArticleCreateDto.ArticleDto.builder()
                .title("title")
                .body("body")
                .description("description")
                .tags(Set.of(tag1, tag2))
                .build();

        ArticleCreateDto dto = ArticleCreateDto.builder()
                .articleDto(articleDto)
                .build();

        // when
        SingleArticleResponseDto result = articleMapperService.createArticle(dto, userId);

        Article article = articleRepository.findBySlug(result.getArticle().getSlug())
                .orElseThrow(() -> new ArticleNotFoundException(result.getArticle().getSlug()));

        // then
        assertThat(article.getTitle()).isEqualTo(dto.getArticleDto().getTitle());
        assertThat(article.getBody()).isEqualTo(dto.getArticleDto().getBody());
        assertThat(article.getDescription()).isEqualTo(dto.getArticleDto().getDescription());
        assertThat(article.getSlug()).isEqualTo(result.getArticle().getSlug());
        assertThat(article.getUserId()).isEqualTo(userId);
    }

    @Test
    void updateArticle() {
        // given
        String slug = "slug101";
        Long userId = 101L;
        TagRequestDto tag1 = TagRequestDto.builder().tag("new_tag1").build();
        TagRequestDto tag2 = TagRequestDto.builder().tag("new_tag2").build();

        ArticleUpdateDto.ArticleDto articleDto = ArticleUpdateDto.ArticleDto.builder()
                .title("new_title")
                .body("new_body")
                .description("new_description")
                .tags(Set.of(tag1, tag2))
                .build();
        ArticleUpdateDto dto = ArticleUpdateDto.builder()
                .articleDto(articleDto)
                .build();

        // when
        SingleArticleResponseDto result = articleMapperService.updateArticle(dto, slug, userId);

        Article article = articleRepository.findBySlug(result.getArticle().getSlug())
                .orElseThrow(() -> new ArticleNotFoundException(result.getArticle().getSlug()));

        // then
        assertThat(article.getTitle()).isEqualTo(dto.getArticleDto().getTitle());
        assertThat(article.getBody()).isEqualTo(dto.getArticleDto().getBody());
        assertThat(article.getDescription()).isEqualTo(dto.getArticleDto().getDescription());
        assertThat(article.getSlug()).isEqualTo(result.getArticle().getSlug());
        assertThat(article.getArticleTags()).extracting("tagId").isNotNull();
        assertThat(article.getUserId()).isEqualTo(userId);
    }

    private Article saveArticle(Tag tag) {
        Article article = Article.builder()
                .userId(101L)
                .title("title")
                .description("description")
                .body("body")
                .build();

        article.addTags(Set.of(tag));

        return articleRepository.save(article);
    }

}
