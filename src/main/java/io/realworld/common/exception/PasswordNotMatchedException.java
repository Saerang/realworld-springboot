package io.realworld.common.exception;

import org.springframework.http.HttpStatus;

public class PasswordNotMatchedException extends AbstractBaseException {

    private static final long serialVersionUID = 3740707809326729296L;

    public PasswordNotMatchedException(Long userId) {
        super("User " + userId + " password not matched.");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.PASSWORD_NOT_MATCHED;
    }
}
