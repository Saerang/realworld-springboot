package io.realworld.tag.domain;

import io.realworld.article.domain.ArticleTag;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
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
    public Tag(String tag) {
        this.tag = tag;
    }
}