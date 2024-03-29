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

    private Long userId;

    @OneToMany(mappedBy = "article", cascade = ALL)
    private final Set<ArticleTag> articleTags = new HashSet<>();

    public Article(String title, String description, String body, Long userId) {
        this(null, title, description, body, userId);
    }

    @Builder
    public Article(Long id, String title, String description, String body, Long userId) {
        Assert.state(StringUtils.isNotBlank(title), "title may not be blank.");
        Assert.state(userId != null, "userId may not be null.");

        this.id = id;
        this.title = title;
        this.slug = this.getReplaceSlug();
        this.description = description;
        this.body = body;
        this.userId = userId;
    }

    public void addTags(Set<Tag> tags) {
        this.articleTags.addAll(tags.stream().map(tag -> new ArticleTag(this, tag.getId())).collect(toSet()));
    }

    public void updateTags(Set<Tag> tags) {
        this.articleTags.clear();
        this.articleTags.addAll(tags.stream().map(tag -> new ArticleTag(this, tag.getId())).collect(toSet()));
    }

    public void updateArticle(String title, String body, String description) {
        if (StringUtils.isNotBlank(title)) {
            this.title = title;
            this.slug = this.getReplaceSlug();
        }
        this.body = StringUtils.isNotBlank(body) ? body : this.body;
        this.description = StringUtils.isNotBlank(description) ? description : this.description;
    }

    // TODO: slug 로직 찾아보기.
    private String getReplaceSlug() {
        return this.title.replaceAll(" ", "-") + "-" + RandomStringUtils.randomAlphanumeric(10);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                '}';
    }
}
