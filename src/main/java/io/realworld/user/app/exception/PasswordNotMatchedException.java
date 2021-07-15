package io.realworld.user.app.exception;

public class PasswordNotMatchedException extends RuntimeException {
    public PasswordNotMatchedException(long userId) {
        super("User " + userId + " password not matched.");
    }
}
