package io.realworld.user.app.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        this(null, null, null);
    }

    public UserNotFoundException(Long userId) {
        this(userId, null, null);
    }

    public UserNotFoundException(String username) {
        this(null, username, null);
    }

    public UserNotFoundException(Long userId, String username, Throwable cause) {
        super("User " + (userId != null ? "userId:" + userId + " " : username != null ? "userName:" + username + " " : "") + "dose not found.", cause);
    }
}
