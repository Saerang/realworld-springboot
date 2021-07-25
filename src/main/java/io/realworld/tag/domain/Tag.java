package io.realworld.tag.domain;

import io.realworld.article.domain.ArticleTag;
import io.realworld.common.base.BaseTimeEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@EqualsAndHashCode(of = {"tag"}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseTimeEntity {
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
        Assert.state(StringUtils.isNotBlank(tag), "tag may not be blank.");
        this.tag = tag;
    }

}
