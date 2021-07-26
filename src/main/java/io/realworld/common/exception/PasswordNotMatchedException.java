package io.realworld.common.exception;

public class PasswordNotMatchedException extends RuntimeException {
    public PasswordNotMatchedException(Long userId) {
        super("User " + userId + " password not matched.");
    }
}
