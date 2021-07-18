package io.realworld.common.exception;

public class PasswordNotMatchedException extends RuntimeException {
    public PasswordNotMatchedException(long userId) {
        super("User " + userId + " password not matched.");
    }
}
