package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleTagDto;
import io.realworld.article.domain.ArticleTag;
import io.realworld.tag.domain.Tag;
import io.realworld.user.app.dto.Mappers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ArticleTagServiceTest {

    @Autowired
    ArticleTagService articleTagService;

    @Test
    void getArticleTags_byArticleIds() {
        //given
        List<Long> articleIds = List.of(101L);

        //when
        List<ArticleTag> articleTags = articleTagService.getArticleTags(articleIds);

        //then
        assertThat(articleTags).hasSize(1);
        assertThat(articleTags).extracting("tag.tag").containsExactly("tag101");
    }

    @Test
    void getArticleTags_byArticleIds_gourp() {
        //given
        List<Long> articleIds = List.of(101L);
        List<ArticleTag> articleTags = articleTagService.getArticleTags(articleIds);

        //when
        Map<Long, List<ArticleTagDto>> articleTagDtoMap = articleTags.stream()
                .map(Mappers::toArticleTagDto)
                .collect(Collectors.groupingBy(ArticleTagDto::getArticleId));

        //then
        List<ArticleTagDto> articleTagDtos = articleTagDtoMap.get(101L);
        assertThat(articleTagDtos).extracting("articleId").containsExactly(101L);
        assertThat(articleTagDtos).extracting("tag").containsExactly(articleTags.get(0).getTag().getTag());
    }


}