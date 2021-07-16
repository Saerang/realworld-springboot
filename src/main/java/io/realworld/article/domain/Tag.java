package io.realworld.article.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_id")
    private Long id;

    private String tag;

    @OneToMany(mappedBy = "tag")
    private Set<ArticleTag> articleTags;

    @Builder
    public Tag(String tag, Set<ArticleTag> articleTags) {
        this.tag = tag;
        this.articleTags = articleTags;
    }
}
