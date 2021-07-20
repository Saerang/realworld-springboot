package io.realworld.tag.domain;

import io.realworld.article.domain.Article;
import io.realworld.article.domain.ArticleTag;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@EqualsAndHashCode(of = "tag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_id")
    private Long id;

    @Column(unique = true)
    private String tag;

    @OneToMany(mappedBy = "tag")
    private Set<ArticleTag> articles = new HashSet<>();

    @Builder
    public Tag(String tag) {
        this.tag = tag;
    }
}