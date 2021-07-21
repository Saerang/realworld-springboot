package io.realworld.favourite.app;

import io.realworld.favourite.app.enumerate.FavouriteType;

public interface FavouriteService {
    FavouriteType getFavouriteType();

    boolean isFavourited(long userId, long authorId);

    void favouriteAuthor(long userId, long authorId);

    void unfavoriteAuthor(long userId, long authorId);
}
