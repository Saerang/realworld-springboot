package io.realworld.article.domain;

import io.realworld.tag.domain.Tag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Getter
@Table(uniqueConstraints = {@UniqueConstraint(name = "uix_article_id_tag_id", columnNames = {"article_id", "tag_id"})})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Builder
    public ArticleTag(Article article, Tag tag) {
        Assert.state(tag != null, "tag may not be null.");
        Assert.state(article != null, "article may not be null.");

        this.article = article;
        this.tag = tag;
    }

}
