package io.realworld.common.exception;

public class FavoriteNotFoundException extends RuntimeException {

    public FavoriteNotFoundException() {
        this(null, null);
    }

    public FavoriteNotFoundException(Long userId, Long authorId) {
        super("Favorite userId:" + userId + " authorId:"+ authorId  + " not found.");
    }
}
