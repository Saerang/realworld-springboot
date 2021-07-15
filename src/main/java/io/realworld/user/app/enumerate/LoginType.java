package io.realworld.user.app.enumerate;

public enum LoginType {
    USERNAME("username:"),
    USER_ID("userId:"),
    EMAIL("email:");

    String message;

    LoginType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}