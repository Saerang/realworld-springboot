package io.realworld.user.app;

import io.realworld.user.domain.User;

import java.util.Optional;

public interface AuthenticationService {

    User getCurrentUser();

    Long getCurrentUserId();
}
