package io.realworld.favorite.app;

import io.realworld.favorite.app.enumerate.FavoriteType;
import io.realworld.favorite.domain.Favorite;

import java.util.List;

public interface ArticleFavoriteService {
    FavoriteType getFavoriteType();

    List<Favorite> getFavorites(Long favoritedId);

    List<Favorite> getFavorites(List<Long> favoritedId);

    List<Long> getFavoritedIds(Long userId);

    boolean isFavorited(Long userId, Long favoriteId);

    void favoriteAuthor(Long userId, Long favoriteId);

    void favoriteAuthor(Long userId, String searchFavorite);

    void unfavoriteAuthor(Long userId, Long favoriteId);

    void unfavoriteAuthor(Long userId, String searchFavorite);
}
