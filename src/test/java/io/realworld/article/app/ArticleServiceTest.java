package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;
import io.realworld.common.exception.ArticleNotFound;
import io.realworld.user.domain.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Collections;

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
    void save() {
        // given
        authSetUp("1");
        ArticleCreateDto dto = ArticleCreateDto.builder()
                .title("title")
                .description("description")
                .body("body").build();

        // when
        SingleArticleResponseDto savedDto = articleService.createArticle(dto);

        // TODO : DB 확인은 어떤 값으로 할지 고민
        //em.flush();
        //em.clear();
        //articleRepository.findBy(savedDto.getArticle()).orElseThrow(() -> new ArticleNotFound();

        // then
        assertThat(savedDto.getArticle().getTitle()).isEqualTo("title");
        assertThat(savedDto.getArticle().getDescription()).isEqualTo("description");
        assertThat(savedDto.getArticle().getBody()).isEqualTo("body");
    }

    private void authSetUp(String userId) {
        org.springframework.security.core.userdetails.User principal = new org.springframework.security.core.userdetails.User(userId, "", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal, "token", Collections.emptyList()));
    }
}
