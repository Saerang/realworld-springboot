package io.realworld.user.app;

import io.realworld.user.domain.User;

public interface AuthenticationService {

    User getCurrentUser();
}
