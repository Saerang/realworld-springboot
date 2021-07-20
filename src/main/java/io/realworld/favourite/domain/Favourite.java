package io.realworld.favourite.domain;

import io.realworld.favourite.app.enumerate.FavouriteType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Favourite {
    @EmbeddedId
    private FavouriteId favouriteId;

    protected Favourite() {
    }

    @Builder
    public Favourite(long userId, long authorId, FavouriteType favouriteType) {
        this(FavouriteId.builder().userId(userId).authorId(authorId).favouriteType(favouriteType).build());
    }

    public Favourite(FavouriteId favouriteId) {
        this.favouriteId = favouriteId;
    }

}
