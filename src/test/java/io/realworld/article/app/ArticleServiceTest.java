package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.domain.Article;
import io.realworld.article.domain.repository.ArticleRepository;
import io.realworld.common.WithDefaultUser;
import io.realworld.common.exception.ArticleNotFound;
import io.realworld.tag.app.dto.TagRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ArticleServiceTest {

    @Autowired
    ArticleService articleService;
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
    void getArticles() {
        //given

        //when
//        articleService.getArticles()

        //then
    }

}
