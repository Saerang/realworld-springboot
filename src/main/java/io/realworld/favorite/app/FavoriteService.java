package io.realworld.favorite.app;

import io.realworld.favorite.app.enumerate.FavoriteType;
import io.realworld.favorite.domain.Favorite;

import java.util.List;

public interface FavoriteService {
    FavoriteType getFavoriteType();

    boolean isFavorited(long userId, long favoriteId);

    List<Favorite> getFavorites(long favoritedId);

    void favoriteAuthor(long userId, long favoriteId);

    void unfavoriteAuthor(long userId, long favoriteId);
}
