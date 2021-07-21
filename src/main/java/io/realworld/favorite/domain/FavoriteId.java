package io.realworld.favorite.domain;

import io.realworld.favorite.app.enumerate.FavoriteType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@Embeddable
public class FavoriteId implements Serializable {
    private static final long serialVersionUID = 8441319845505079586L;

    private Long userId;

    private Long favoritedId;

    @Enumerated(EnumType.STRING)
    private FavoriteType favoriteType;

    protected FavoriteId() {
    }

    @Builder
    public FavoriteId(Long userId, Long favoritedId, FavoriteType favoriteType) {
        Assert.state(userId != null, "userId may not be null.");
        Assert.state(favoritedId != null, "favoritedId may not be null.");
        Assert.state(favoriteType != null, "favoriteType may not be null.");

        this.userId = userId;
        this.favoritedId = favoritedId;
        this.favoriteType = favoriteType;
    }
}
