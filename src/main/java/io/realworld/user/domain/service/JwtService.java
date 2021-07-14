package io.realworld.user.domain.service;

import io.realworld.user.domain.User;

public interface JwtService {
    String createToken(User user);
}
