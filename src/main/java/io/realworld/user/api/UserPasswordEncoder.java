package io.realworld.user.api;

public interface UserPasswordEncoder {
    String encode(String password);

    boolean matches(CharSequence rawPassword, String encodedPassword);
}
