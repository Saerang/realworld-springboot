package io.realworld.common.exception;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException() {
        this(null);
    }

    public ArticleNotFoundException(String slug) {
        super("Article " + (slug != null ? slug + " " : "") + "dose not found.");
    }
}
