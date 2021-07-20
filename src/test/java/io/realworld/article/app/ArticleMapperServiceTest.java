package io.realworld.article.app;

import io.realworld.article.api.dto.MultipleArticleSearchDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;
import io.realworld.article.api.dto.SingleArticleSearchDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ArticleMapperServiceTest {

    @Autowired
    ArticleMapperService articleMapperService;

    @Test
    void getArticleResponseDto() {
        // given
        SingleArticleSearchDto dto = SingleArticleSearchDto.builder().slug("slug").build();

        // when
        SingleArticleResponseDto result = articleMapperService.getArticle(dto, 1);

        // then
        assertThat(result.getArticle()).isEqualTo(dto.getSlug());
    }

}
