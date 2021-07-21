package io.realworld.common.exception;

public class FavouriteNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1947898325013621298L;

    public FavouriteNotFoundException() {
        super("Favourite not found.");
    }
}
