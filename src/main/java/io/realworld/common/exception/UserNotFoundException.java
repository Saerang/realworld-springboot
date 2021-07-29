package io.realworld.common.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AbstractBaseException{

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.USER_NOT_FOUND;
    }

    public UserNotFoundException() {
        this(null);
    }

    public UserNotFoundException(String user) {
        this(user, null);
    }

    public UserNotFoundException(String user, Throwable cause) {
        super("User " + (StringUtils.isNotEmpty(user) ? user + " " : "") + "dose not found.", cause);
    }
}
