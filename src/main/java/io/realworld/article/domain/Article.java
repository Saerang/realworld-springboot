package io.realworld.article.domain;

import io.realworld.common.base.BaseTimeEntity;
import io.realworld.tag.domain.Tag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(unique = true)
    private String slug;

    private String title;

    private String description;

    private String body;

    @OneToMany(mappedBy = "article", cascade = ALL)
    private final Set<ArticleTag> articleTags = new HashSet<>();

    private Long userId;

    @Builder
    public Article(String title, String description, String body, Long userId) {
        Assert.state(StringUtils.isNotBlank(title), "title may not be blank.");
        Assert.state(userId != null, "userId may not be null.");

        this.title = title;
        this.slug = this.getReplaceSlug();
        this.description = description;
        this.body = body;
        this.userId = userId;
    }

    public void addTags(Set<Tag> tags) {
        this.articleTags.addAll(tags.stream().map(tag -> new ArticleTag(this, tag)).collect(toSet()));
    }

    public void updateArticle(String title, String body, String description) {
        this.title = title;
        this.slug = this.getReplaceSlug();
        this.body = body;
        this.description = description;
    }

    // TODO: slug 로직 찾아보기.
    private String getReplaceSlug() {
        return this.title.replaceAll(" ", "-") + "-" + RandomStringUtils.randomAlphanumeric(10);
    }
}
