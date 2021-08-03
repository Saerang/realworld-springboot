package io.realworld.article.domain;

import io.realworld.common.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Getter
@Table(uniqueConstraints = {@UniqueConstraint(name = "uix_article_id_tag_id", columnNames = {"article_id", "tag_id"})})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleTag extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name = "tag_id")
    private Long tagId;

    @Builder
    public ArticleTag(Article article, Long tagId) {
        Assert.state(tagId != null, "tagId may not be null.");
        Assert.state(article != null, "article may not be null.");

        this.article = article;
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "ArticleTag{" +
                "id=" + id +
                ", article=" + article +
                ", tagId=" + tagId +
                '}';
    }

}
