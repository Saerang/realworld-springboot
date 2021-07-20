package io.realworld.article.domain;

import io.realworld.tag.domain.Tag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static javax.persistence.CascadeType.PERSIST;

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

    @OneToMany(mappedBy = "article", cascade = PERSIST)
    private Set<ArticleTag> articleTags = new HashSet<>();

    private boolean favorited;

    private int favoritesCount;

    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public Article(String slug, String title, String description, String body, boolean favorited, int favoritesCount, Long userId) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.favorited = favorited;
        this.favoritesCount = favoritesCount;
        this.userId = userId;
    }

    public void addTags(Set<Tag> tags) {
        this.articleTags.addAll(tags.stream().map(tag -> new ArticleTag(this, tag)).collect(toSet()));
    }

}
