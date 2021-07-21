package io.realworld.favourite.app;

import io.realworld.favourite.app.enumerate.FavouriteType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FavouriteServiceFactory {

    private final Map<FavouriteType, FavouriteService> favouriteServiceMap = new HashMap<>();

    public FavouriteServiceFactory(List<FavouriteService> favouriteServices) {

        if(CollectionUtils.isEmpty(favouriteServices)) {
            throw new IllegalArgumentException("FavouriteService not found.");
        }

        for (FavouriteService favouriteService : favouriteServices) {
            this.favouriteServiceMap.put(favouriteService.getFavouriteType(), favouriteService);
        }
    }

    public FavouriteService getService(FavouriteType favouriteType) {
        return favouriteServiceMap.get(favouriteType);
    }
}
