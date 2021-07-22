package io.realworld.favorite.app;

import io.realworld.favorite.app.enumerate.FavoriteType;
import io.realworld.favorite.domain.Favorite;

import java.util.List;

public interface FavoriteService {
    FavoriteType getFavoriteType();

    List<Favorite> getFavorites(long favoritedId);

    List<Favorite> getFavorites(List<Long> favoritedId);

    List<Long> getFavoritedIds(long userId);

    boolean isFavorited(long userId, long favoriteId);

    void favoriteAuthor(long userId, long favoriteId);

    void unfavoriteAuthor(long userId, long favoriteId);
}
