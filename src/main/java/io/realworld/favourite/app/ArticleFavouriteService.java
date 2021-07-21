package io.realworld.favourite.app;

import io.realworld.favourite.app.enumerate.FavouriteType;
import io.realworld.favourite.domain.Favourite;
import io.realworld.favourite.domain.FavouriteId;
import io.realworld.favourite.domain.repository.FavouriteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleFavouriteService implements FavouriteService{

    final private FavouriteRepository favouriteRepository;

    public ArticleFavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    @Override
    public FavouriteType getFavouriteType() {
        return FavouriteType.ARTICLE;
    }

    @Override
    public boolean isFavourited(long userId, long authorId) {
        FavouriteId favouriteId = FavouriteId.builder()
                .userId(userId)
                .authorId(authorId)
                .favouriteType(FavouriteType.ARTICLE)
                .build();

        return favouriteRepository.findById(favouriteId).isPresent();
    }

    @Override
    public void favouriteAuthor(long userId, long authorId) {
        Favourite favourite = Favourite.builder()
                .userId(userId)
                .authorId(authorId)
                .favouriteType(FavouriteType.ARTICLE)
                .build();

        favouriteRepository.save(favourite);
    }

    @Override
    public void unfavoriteAuthor(long userId, long authorId) {
        Favourite favourite = Favourite.builder()
                .userId(userId)
                .authorId(authorId)
                .favouriteType(FavouriteType.ARTICLE)
                .build();

        favouriteRepository.delete(favourite);
    }

}
