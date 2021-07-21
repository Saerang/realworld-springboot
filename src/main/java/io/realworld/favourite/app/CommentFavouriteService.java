package io.realworld.favourite.app;

import io.realworld.favourite.app.enumerate.FavouriteType;
import io.realworld.favourite.domain.Favourite;
import io.realworld.favourite.domain.repository.FavouriteRepository;
import org.springframework.stereotype.Service;

// TODO: 추후 댓글 좋아요 만들면 사용.
@Service
public class CommentFavouriteService implements FavouriteService {
    final private FavouriteRepository favouriteRepository;

    public CommentFavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    @Override
    public FavouriteType getFavouriteType() {
        return FavouriteType.COMMENT;
    }

    @Override
    public boolean isFavourited(long userId, long authorId) {
        return false;
    }

    @Override
    public void favouriteAuthor(long userId, long authorId) {
    }

    @Override
    public void unfavoriteAuthor(long userId, long authorId) {
    }
}
