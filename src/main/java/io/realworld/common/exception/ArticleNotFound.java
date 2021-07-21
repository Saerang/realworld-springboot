package io.realworld.common.exception;

public class ArticleNotFound extends RuntimeException {

    public ArticleNotFound() {
        this(null);
    }

    public ArticleNotFound(Long articleId) {
        super("Article " + (articleId != null ? articleId + " " : "") + "dose not found.");
    }
}
