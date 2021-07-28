package io.realworld.comment.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
public class Comment  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private Long articleId;

    @Column(nullable = false)
    private Long userId;

    private String body;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public Comment(Long articleId, Long userId, String body) {
        this(null, articleId, userId, body);
    }

    @Builder
    public Comment(Long id, Long articleId, Long userId, String body) {
        this.id = id;
        this.articleId = articleId;
        this.userId = userId;
        this.body = body;
    }

    @PrePersist
    public void initDate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
