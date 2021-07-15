package io.realworld.user.app.exception;

public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist() {
        this(null);
    }

    public UserAlreadyExist(String userId) {
        super("User " + userId + "already exist.");
    }
}
