package io.realworld.favorite.app;

import io.realworld.favorite.app.enumerate.FavoriteType;
import io.realworld.favorite.domain.Favorite;
import io.realworld.favorite.domain.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: 추후 댓글 좋아요 만들면 사용.
@Service
public class CommentFavoriteService implements FavoriteService {
    final private FavoriteRepository favoriteRepository;

    public CommentFavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public FavoriteType getFavoriteType() {
        return FavoriteType.COMMENT;
    }

    @Override
    public List<Favorite> getFavorites(Long favoriteId) {
        return null;
    }

    @Override
    public List<Favorite> getFavorites(List<Long> favoritedId) {
        return null;
    }

    @Override
    public List<Long> getFavoritedIds(Long userId) {
        return null;
    }

    @Override
    public boolean isFavorited(Long userId, Long favoriteId) {
        return false;
    }

    @Override
    public void favoriteAuthor(Long userId, Long favoriteId) {
    }

    @Override
    public void favoriteAuthor(Long userId, String slug) {

    }

    @Override
    public void unfavoriteAuthor(Long userId, Long favoriteId) {
    }

    @Override
    public void unfavoriteAuthor(Long userId, String slug) {

    }
}
