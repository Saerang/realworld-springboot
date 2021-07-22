package io.realworld.common.exception;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException() {
        this(null);
    }

    public ArticleNotFoundException(Long articleId) {
        super("Article " + (articleId != null ? articleId + " " : "") + "dose not found.");
    }
}
