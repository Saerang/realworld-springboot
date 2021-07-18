package io.realworld.common.exception;

public class ArticleNotFound extends RuntimeException {

    public ArticleNotFound(Long articleId) {
        super("Article " + articleId + "dose not found.");
    }
}
