package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.ArticleResponseDto;
import io.realworld.article.domain.Article;
import io.realworld.common.exception.ArticleNotFound;
import io.realworld.user.domain.repository.ArticleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

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
    void save() {
        // given
        ArticleCreateDto dto = ArticleCreateDto.builder().build();

        // when
        ArticleResponseDto savedDto = articleService.createArticle(dto);

        // then
//        Article findArticle = articleRepository.findById(savedDto.getId()).orElseThrow(() -> new ArticleNotFound(savedArticle.getId()));
//
//        assertThat(savedDto.()).isEqualTo(findArticle.getId());
    }

}
