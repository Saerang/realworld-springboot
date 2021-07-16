package io.realworld.article.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;
    private String slug;
    private String title;
    private String description;
    private String body;
    @OneToMany(mappedBy = "article")
    private Set<ArticleTag> articleTags = new HashSet<>();
    private boolean favorited;
    private int favoritesCount;
    private Long userId;

    @Builder
    public Article(String slug, String title, String description, String body, Set<ArticleTag> articleTags, boolean favorited, int favoritesCount, Long userId) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.articleTags = articleTags;
        this.favorited = favorited;
        this.favoritesCount = favoritesCount;
        this.userId = userId;
    }
}
