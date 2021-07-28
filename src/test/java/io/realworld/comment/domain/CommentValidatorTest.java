package io.realworld.comment.domain;

import io.realworld.article.domain.Article;
import io.realworld.article.domain.repository.ArticleRepository;
import io.realworld.common.exception.ArticleNotFoundException;
import io.realworld.common.exception.UserNotFoundException;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static io.realworld.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentValidatorTest {

    @Mock
    ArticleRepository articleRepository;
    @Mock
    UserRepository userRepository;

    private CommentValidator validator;

    @BeforeEach
    void setUp() {
        validator = new CommentValidator(articleRepository, userRepository);
    }

    @Test
    void article_and_user_isExist_true() {
        // given
        Comment comment = aComment().build();
        Article article = anArticle().build();

        when(articleRepository.findById(comment.getArticleId())).thenReturn(Optional.of(article));
        when(userRepository.findById(comment.getUserId())).thenReturn(Optional.of(aUser().build()));

        // when
        validator.validate(comment);

        // then
        verify(articleRepository).findById(comment.getArticleId());
        verify(userRepository).findById(comment.getUserId());
    }

    @Test
    void article_isExist_false() {
        // given
        Comment comment = aComment().build();
        lenient().when(userRepository.findById(comment.getUserId())).thenReturn(Optional.of(aUser().build()));

        // when
        // then
        assertThatThrownBy(() -> validator.validate(comment))
                .isInstanceOf(ArticleNotFoundException.class);
    }

    @Test
    void user_isExist_false() {
        // given
        Comment comment = aComment().build();
        Article article = anArticle().build();
        lenient().when(articleRepository.findById(comment.getArticleId())).thenReturn(Optional.of(article));

        // when
        // then
        assertThatThrownBy(() -> validator.validate(comment)).isInstanceOf(UserNotFoundException.class);
    }
}
