package io.realworld.favorite.app;

import io.realworld.favorite.app.enumerate.FavoriteType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FavoriteServiceFactory {

    private final Map<FavoriteType, FavoriteService> favoriteServiceMap = new HashMap<>();

    public FavoriteServiceFactory(List<FavoriteService> favoriteServices) {

        if(CollectionUtils.isEmpty(favoriteServices)) {
            throw new IllegalArgumentException("FavoriteService not found.");
        }

        for (FavoriteService favoriteService : favoriteServices) {
            this.favoriteServiceMap.put(favoriteService.getFavoriteType(), favoriteService);
        }
    }

    public FavoriteService getService(FavoriteType favoriteType) {
        return favoriteServiceMap.get(favoriteType);
    }
}
