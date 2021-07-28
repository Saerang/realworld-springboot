package io.realworld.common.exception;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException() {
        this(null, null);
    }

    public ArticleNotFoundException(String slug) {
        this(null, slug);
    }

    public ArticleNotFoundException(Long articleId) {
        this(articleId, null);
    }

    public ArticleNotFoundException(Long articleId, String slug) {
        super("Article " + (articleId != null ? articleId + " " : slug != null ? slug + " " : "") + "dose not found.");
    }
}
