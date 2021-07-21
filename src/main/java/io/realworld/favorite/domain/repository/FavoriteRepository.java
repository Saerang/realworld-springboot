package io.realworld.favorite.domain.repository;

import io.realworld.favorite.app.enumerate.FavoriteType;
import io.realworld.favorite.domain.Favorite;
import io.realworld.favorite.domain.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
    @Query("select f from Favorite f where f.favoriteId.favoritedId = :favoritedId and f.favoriteId.favoriteType = :favoriteType")
    List<Favorite> findByFavoritedIdAndFavoriteType(@Param("favoritedId") long favoritedId, @Param("favoriteType") FavoriteType favoriteType);
}
