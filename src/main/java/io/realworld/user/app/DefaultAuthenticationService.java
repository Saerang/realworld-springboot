package io.realworld.user.app;

import io.realworld.common.exception.UserNotFoundException;
import io.realworld.user.app.enumerate.LoginType;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static io.realworld.user.app.enumerate.LoginType.*;

@Service
@RequiredArgsConstructor
public class DefaultAuthenticationService implements AuthenticationService {

    final private UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new UserNotFoundException();
        }

        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(EMAIL.getMessage() + email));
    }
}
