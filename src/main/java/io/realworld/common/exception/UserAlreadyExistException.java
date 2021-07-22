package io.realworld.common.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(Long userId) {
        super("User " + userId + " already exist.");
    }
}
