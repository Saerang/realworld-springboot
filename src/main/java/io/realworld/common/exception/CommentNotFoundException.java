package io.realworld.common.exception;

import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends AbstractBaseException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.COMMENT_NOT_FOUND;
    }

    public CommentNotFoundException() {
        this(null);
    }

    public CommentNotFoundException(Long commentId) {
        super("Comment " + (commentId != null ? commentId + " " : "") + "not found.");
    }
}
