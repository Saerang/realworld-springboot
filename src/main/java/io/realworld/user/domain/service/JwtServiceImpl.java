package io.realworld.user.domain.service;

import io.realworld.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class JwtServiceImpl implements JwtService {

    @Override
    public String createToken(User user) {
        return "token";
    }
}
