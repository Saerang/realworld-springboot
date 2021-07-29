package io.realworld.common.exception;

import org.springframework.http.HttpStatus;

public class TagNotFoundException extends AbstractBaseException {

    private static final long serialVersionUID = 879795716071341051L;

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.TAG_NOT_FOUND;
    }

    public TagNotFoundException(String tag) {
        super("Tag " + tag + " not found.");
    }
}
