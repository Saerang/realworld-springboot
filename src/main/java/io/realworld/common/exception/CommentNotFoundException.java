package io.realworld.common.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException() {
        this(null);
    }

    public CommentNotFoundException(Long commentId) {
        super("Comment " + (commentId != null ? commentId + " " : "") + "not found.");
    }
}
