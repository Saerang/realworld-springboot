package io.realworld.common.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends AbstractBaseException {

    private static final long serialVersionUID = 2790961961441370486L;

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }

    @Override
    public ErrorCode getErrorCode() {
        return null;
    }

    public UserAlreadyExistException(Long userId) {
        super("User " + userId + " already exist.");
    }
}
