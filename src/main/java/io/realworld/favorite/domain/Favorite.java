package io.realworld.favorite.domain;

import io.realworld.favorite.app.enumerate.FavoriteType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Entity
public class Favorite {
    @EmbeddedId
    private FavoriteId favoriteId;

    protected Favorite() {
    }

    @Builder
    public Favorite(long userId, long favoritedId, FavoriteType favoriteType) {
        this(FavoriteId.builder().userId(userId).favoritedId(favoritedId).favoriteType(favoriteType).build());
    }

    public Favorite(FavoriteId favoriteId) {
        this.favoriteId = favoriteId;
    }

}
