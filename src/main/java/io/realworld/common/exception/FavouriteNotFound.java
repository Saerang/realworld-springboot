package io.realworld.common.exception;

public class FavouriteNotFound extends RuntimeException {

    public FavouriteNotFound(long userId, long authorId) {
        super("Favourite userId:" + userId + " authorId:"+ authorId  + " not found.");
    }
}
