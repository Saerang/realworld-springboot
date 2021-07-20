package io.realworld.common.exception;

public class TagNotFoundException extends RuntimeException {

    public TagNotFoundException(String tag) {
        super("Tag " + tag + " not found.");
    }
}
