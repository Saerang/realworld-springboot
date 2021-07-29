package io.realworld.common.exception;

public enum ErrorCode {
    // Common
    INTERNAL_ERROR,

    // User
    USER_NOT_FOUND,
    USER_ALREADY_EXIST,
    PASSWORD_NOT_MATCHED,

    // Article
    ARTICLE_NOT_FOUND,

    // Favorite
    FAVORITE_NOT_FOUND,

    // Tag
    TAG_NOT_FOUND,

    // Comment
    COMMENT_NOT_FOUND

}
