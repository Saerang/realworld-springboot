package io.realworld.common.exception;

import org.springframework.http.HttpStatus;

public class FavoriteNotFoundException extends AbstractBaseException {

    private static final long serialVersionUID = 8237871384630462397L;

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.FAVORITE_NOT_FOUND;
    }

    public FavoriteNotFoundException() {
        this(null, null);
    }

    public FavoriteNotFoundException(Long userId, Long authorId) {
        super("Favorite userId:" + userId + " authorId:"+ authorId  + " not found.");
    }
}
