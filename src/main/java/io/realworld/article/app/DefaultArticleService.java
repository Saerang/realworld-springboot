package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;
import io.realworld.article.domain.Article;
import io.realworld.user.app.UserService;
import io.realworld.user.app.dto.Mappers;
import io.realworld.user.domain.FollowRelation;
import io.realworld.user.domain.FollowRelationId;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultArticleService implements ArticleService{

    final private UserService userService;
    final private UserRepository userRepository;
    final private FollowRelationRepository followRelationRepository;
    final private ArticleRepository articleRepository;
    final private TagRepository tagRepository;
    final private ArticleTagRepository articleTagRepository;

    @Override
    public SingleArticleResponseDto createArticle(ArticleCreateDto dto) {
        Article article = Article.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .body(dto.getBody())
                .build();

        Article savedArticle = articleRepository.save(article);
        User currentUser = userService.findCurrentUser();
        User user = userService.getUserById(savedArticle.getUserId());
        Optional<FollowRelation> followRelation = followRelationRepository.findById(new FollowRelationId(user.getId(), currentUser.getId()));

        return Mappers.toSingleArticleResponseDto(savedArticle, user, followRelation.isPresent());
    }
}
