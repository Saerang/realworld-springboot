package io.realworld;

import io.realworld.article.domain.Article;
import io.realworld.comment.domain.Comment;
import io.realworld.comment.domain.Comment.CommentBuilder;
import io.realworld.user.api.SpringUserPasswordEncoder;
import io.realworld.user.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static io.realworld.article.domain.Article.ArticleBuilder;
import static io.realworld.user.domain.User.*;

public class Fixtures {
    public static CommentBuilder aComment() {
        return Comment.builder()
                .id(1L)
                .articleId(anArticle().build().getId())
                .userId(aUser().build().getId())
                .body("body");
    }

    public static ArticleBuilder anArticle() {
        return Article.builder()
                .id(1L)
                .userId(aUser().build().getId())
                .title("title")
                .body("body")
                .description("description");
    }

    public static UserBuilder aUser() {
        return User.builder()
                .id(1L)
                .username("realworld")
                .password("12345678")
                .email("realwrold@email.com")
                .bio("bio")
                .image("image")
                .userPasswordEncoder(new SpringUserPasswordEncoder(new BCryptPasswordEncoder()));
    }

    //(101, 'realworld101@email.com', '$2a$10$/Hxqaf3ZfncnQGn2/Qg2R.Uacd2ElztD.4viYFF6jPHeBrqoG9M/m', 'bio101', 'image101', 'realworld101'),
    public static User defaultUser() {
        return User.builder()
                .id(101L)
                .username("realworld101")
                .password("$2a$10$/Hxqaf3ZfncnQGn2/Qg2R.Uacd2ElztD.4viYFF6jPHeBrqoG9M/m")
                .email("realworld101@email.com")
                .bio("bio101")
                .image("image101")
                .userPasswordEncoder(new SpringUserPasswordEncoder(new BCryptPasswordEncoder()))
                .build();
    }
}
