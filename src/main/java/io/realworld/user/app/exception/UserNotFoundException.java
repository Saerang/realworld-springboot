package io.realworld.user.app.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username) {
        this(username, null);
    }

    public UserNotFoundException(String username, Throwable cause) {
        super("User " + username + " not found.", cause);
    }
}
