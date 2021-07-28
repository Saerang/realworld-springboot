package io.realworld.comment.domain;

import io.realworld.article.domain.Article;
import io.realworld.article.domain.repository.ArticleRepository;
import io.realworld.common.exception.ArticleNotFoundException;
import io.realworld.common.exception.UserNotFoundException;
import io.realworld.user.app.enumerate.LoginType;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class CommentValidator {
    final private ArticleRepository articleRepository;
    final private UserRepository userRepository;


    public CommentValidator(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public void validate(Comment comment) {
        getArticle(comment);
        getUser(comment);
    }

    private Article getArticle(Comment comment) {
        return articleRepository.findById(comment.getArticleId())
                .orElseThrow(() -> new ArticleNotFoundException(comment.getArticleId()));
    }

    private User getUser(Comment comment) {
        return userRepository.findById(comment.getUserId())
                .orElseThrow(() -> new UserNotFoundException(LoginType.USER_ID.getMessage() + comment.getUserId()));
    }

}
