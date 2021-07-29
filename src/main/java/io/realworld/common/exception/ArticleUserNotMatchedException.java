package io.realworld.common.exception;

import org.springframework.http.HttpStatus;

public class ArticleUserNotMatchedException extends AbstractBaseException {
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public ErrorCode getErrorCode() {
        return null;
    }
}
