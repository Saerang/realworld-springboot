package io.realworld.favourite.domain;

import io.realworld.favourite.app.enumerate.FavouriteType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@Embeddable
public class FavouriteId implements Serializable {
    private static final long serialVersionUID = 8441319845505079586L;

    private Long userId;

    private Long authorId;

    private FavouriteType favouriteType;

    protected FavouriteId() {
    }

    @Builder
    public FavouriteId(Long userId, Long authorId, FavouriteType favouriteType) {
        this.userId = userId;
        this.authorId = authorId;
        this.favouriteType = favouriteType;
    }
}

