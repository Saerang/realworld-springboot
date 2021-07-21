package io.realworld.article.app;

import io.realworld.article.api.dto.SingleArticleResponseDto;
import io.realworld.article.api.dto.SingleArticleSearchDto;
import io.realworld.article.domain.Article;
import io.realworld.article.domain.repository.ArticleRepository;
import io.realworld.tag.domain.Tag;
import io.realworld.tag.domain.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

        Article savedArticle = saveArticle("slug", "title", "body", tag);

        SingleArticleSearchDto dto = SingleArticleSearchDto.builder().slug(savedArticle.getSlug()).build();

        // when
        SingleArticleResponseDto result = articleMapperService.getSingleArticleResponseDto(dto, 1);

        // then
        assertThat(result.getArticle().getSlug()).isEqualTo(savedArticle.getSlug());
        assertThat(result.getArticle().getTitle()).isEqualTo(savedArticle.getTitle());
        assertThat(result.getArticle().getBody()).isEqualTo(savedArticle.getBody());
        assertThat(result.getArticle().isFavorited()).isFalse();
        assertThat(result.getArticle().getAuthor().getUsername()).isEqualTo("realworld1");
        assertThat(result.getArticle().getAuthor().getBio()).isEqualTo("bio1");
        assertThat(result.getArticle().getAuthor().getImage()).isEqualTo("image1");
    }


    private Article saveArticle(String slug, String title, String body, Tag tag) {
        Article article = Article.builder()
                .userId(1L)
                .title(title)
                .description("description")
                .body(body)
                .slug(slug)
                .build();

        article.addTags(Set.of(tag));

        return articleRepository.save(article);
    }

}
