package io.realworld.favourite.domain.repository;

import io.realworld.favourite.domain.Favourite;
import io.realworld.favourite.domain.FavouriteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, FavouriteId> {
}
