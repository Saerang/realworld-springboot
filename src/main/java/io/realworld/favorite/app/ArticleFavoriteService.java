package io.realworld.favorite.app;

import io.realworld.favorite.app.enumerate.FavoriteType;
import io.realworld.favorite.domain.Favorite;
import io.realworld.favorite.domain.FavoriteId;
import io.realworld.favorite.domain.repository.FavoriteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.realworld.favorite.app.enumerate.FavoriteType.ARTICLE;

@Service
@Transactional
public class ArticleFavoriteService implements FavoriteService {

    final private FavoriteRepository favoriteRepository;

    public ArticleFavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public FavoriteType getFavoriteType() {
        return ARTICLE;
    }

    @Override
    public boolean isFavorited(long userId, long favoriteId) {
        FavoriteId _favoriteId = FavoriteId.builder()
                .userId(userId)
                .favoritedId(favoriteId)
                .favoriteType(this.getFavoriteType())
                .build();

        return favoriteRepository.findById(_favoriteId).isPresent();
    }

    @Override
    public List<Favorite> getFavorites(long favoriteId) {
        return favoriteRepository.findByFavoritedIdAndFavoriteType(favoriteId, ARTICLE);
    }

    @Override
    public void favoriteAuthor(long userId, long favoritedId) {
        Favorite favorite = Favorite.builder()
                .userId(userId)
                .favoritedId(favoritedId)
                .favoriteType(this.getFavoriteType())
                .build();

        favoriteRepository.save(favorite);
    }

    @Override
    public void unfavoriteAuthor(long userId, long favoritedId) {
        Favorite favorite = Favorite.builder()
                .userId(userId)
                .favoritedId(favoritedId)
                .favoriteType(this.getFavoriteType())
                .build();

        favoriteRepository.delete(favorite);
    }

}
