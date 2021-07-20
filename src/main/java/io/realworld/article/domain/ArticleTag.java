package io.realworld.article.domain;

import io.realworld.tag.domain.Tag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Builder
    public ArticleTag(Article article, Tag tag) {
        this.article = article;
        this.tag = tag;
    }

    public void changeArticle(Article article) {
        this.article = article;
        article.getArticleTags().add(this);
    }

    public void changeTag(Tag tag) {
        this.tag = tag;
        tag.getArticleTags().add(this);
    }
}
