package io.realworld.favorite.app;

import io.realworld.favorite.app.enumerate.FavoriteType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FavoriteServiceFactory {

    private final Map<FavoriteType, ArticleFavoriteService> favoriteServiceMap = new HashMap<>();

    public FavoriteServiceFactory(List<ArticleFavoriteService> favoriteServices) {

        if(CollectionUtils.isEmpty(favoriteServices)) {
            throw new IllegalArgumentException("ArticleFavoriteService not found.");
        }

        for (ArticleFavoriteService favoriteService : favoriteServices) {
            this.favoriteServiceMap.put(favoriteService.getFavoriteType(), favoriteService);
        }
    }

    public ArticleFavoriteService getService(FavoriteType favoriteType) {
        return favoriteServiceMap.get(favoriteType);
    }
}
