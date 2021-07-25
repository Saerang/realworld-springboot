package io.realworld.favorite.domain;

import io.realworld.common.base.BaseTimeEntity;
import io.realworld.favorite.app.enumerate.FavoriteType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Entity
@ToString
public class Favorite extends BaseTimeEntity {
    @EmbeddedId
    private FavoriteId favoriteId;

    protected Favorite() {
    }

    @Builder
    public Favorite(Long userId, Long favoritedId, FavoriteType favoriteType) {
        this(FavoriteId.builder().userId(userId).favoritedId(favoritedId).favoriteType(favoriteType).build());
    }

    public Favorite(FavoriteId favoriteId) {
        Assert.state(favoriteId != null, "favoriteId may not be null.");
        Assert.state(favoriteId.getUserId() != null, "userId may not be null.");
        Assert.state(favoriteId.getFavoritedId() != null, "favoritedId may not be null.");
        this.favoriteId = favoriteId;
    }

}
