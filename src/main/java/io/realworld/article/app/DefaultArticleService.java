package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;
import io.realworld.article.domain.Article;
import io.realworld.user.app.FollowRelationService;
import io.realworld.user.app.UserService;
import io.realworld.user.app.dto.Mappers;
import io.realworld.article.domain.repository.ArticleRepository;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.FollowRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultArticleService implements ArticleService{

    final private UserService userService;
    final private FollowRelationService followRelationService;
    final private ArticleRepository articleRepository;

    @Override
    public SingleArticleResponseDto createArticle(ArticleCreateDto dto, long userId) {
        Article article = Article.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .body(dto.getBody())
                .userId(userId)
                .build();

        Article savedArticle = articleRepository.save(article);

        User user = userService.getUserById(userId);
        boolean following = followRelationService.isFollowing(article.getId(), user.getId());

        return Mappers.toSingleArticleResponseDto(savedArticle, user, following);
    }
}
