package io.realworld.common.exception;

import org.springframework.http.HttpStatus;

public class ArticleNotFoundException extends AbstractBaseException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.ARTICLE_NOT_FOUND;
    }

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
