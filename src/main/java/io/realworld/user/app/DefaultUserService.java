package io.realworld.user.app;

import io.realworld.common.exception.PasswordNotMatchedException;
import io.realworld.common.exception.UserAlreadyExistException;
import io.realworld.common.exception.UserNotFoundException;
import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserLoginRequestDto;
import io.realworld.user.api.dto.UserUpdateRequestDto;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static io.realworld.user.app.enumerate.LoginType.EMAIL;
import static io.realworld.user.app.enumerate.LoginType.USER_ID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    final private UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserCreateRequestDto dto) {
        Optional<User> optionalUser = userRepository.findByEmailOrUsername(dto.getEmail(), dto.getUsername());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistException(optionalUser.get().getId());
        }

        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncode(dto.getPassword()))
                .username(dto.getUsername())
                .build();

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        // ToDo: service 분리하는게 좋아보임.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new UserNotFoundException();
        }

        return getUserByEmail(authentication.getName());
    }

    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(USER_ID.getMessage() + userId));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public User updateUser(UserUpdateRequestDto dto) {
        Optional<User> findUser = userRepository.findByEmailOrUsername(dto.getEmail(), dto.getUsername());

        if (findUser.isPresent()) {
            throw new UserAlreadyExistException(findUser.get().getId());
        }

        User user = getCurrentUser();
        user.updateUserInfo(dto.getEmail(), dto.getUsername(), passwordEncode(dto.getPassword()), dto.getImage(), dto.getBio());
        return user;
    }

    @Override
    public User login(UserLoginRequestDto dto) {
        User user = getUserByEmail(dto.getEmail());

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchedException(user.getId());
        }

        return user;
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(EMAIL.getMessage() + email));
    }

    private String passwordEncode(String password) {
        return passwordEncoder.encode(password);
    }


}
