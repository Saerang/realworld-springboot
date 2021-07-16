package io.realworld.article.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_id")
    private Long id;
    private String slug;
    private String title;
    private String description;
    private String body;
    @OneToMany
    @JoinColumn(name = "tag_id")
    private List<Tag> tagList = new ArrayList<>();
    private boolean favorited;
    private int favoritesCount;
    private Long userId;

    @Builder
    public Article(String slug, String title, String description, String body, List<Tag> tagList, boolean favorited, int favoritesCount, Long userId) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.tagList = tagList;
        this.favorited = favorited;
        this.favoritesCount = favoritesCount;
        this.userId = userId;
    }
}
