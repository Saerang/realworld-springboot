package io.realworld.common.exception;

import org.apache.commons.lang3.StringUtils;

public class UserNotFoundException extends RuntimeException{

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
