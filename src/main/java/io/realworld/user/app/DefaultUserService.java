package io.realworld.user.app;

import io.realworld.common.security.JwtTokenProvider;
import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserLoginRequestDto;
import io.realworld.user.api.dto.UserResponseDto;
import io.realworld.user.api.dto.UserUpdateRequestDto;
import io.realworld.user.app.dto.Mappers;
import io.realworld.user.app.exception.PasswordNotMatchedException;
import io.realworld.user.app.exception.UserAlreadyExist;
import io.realworld.user.app.exception.UserNotFoundException;
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
    final private JwtTokenProvider jwtTokenProvider;
    final private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(UserCreateRequestDto dto) {
        Optional<User> optionalUser = userRepository.findByProfile_Username(dto.getUsername());
        if(optionalUser.isPresent()) {
            throw new UserAlreadyExist();
        }

        User user = new User(dto.getEmail(), passwordEncoder.encode(dto.getPassword()), dto.getUsername());
        User saveUser = userRepository.save(user);

        return getUserResponseDto(saveUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getCurrentUser() {
        // ToDo: service 분리하는게 좋아보임.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            throw new UserNotFoundException ();
        }

        long userId = Long.parseLong(authentication.getName());

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(USER_ID.getMessage() + userId));
        return getUserResponseDto(user);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByProfile_Username(username);
    }

    @Override
    public UserResponseDto updateUser(UserUpdateRequestDto dto) {
        User user = userRepository.save(dto.toEntity());
        return getUserResponseDto(user);
    }

    @Override
    public UserResponseDto login(UserLoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new UserNotFoundException(EMAIL.getMessage() + dto.getEmail()));

        if(!dto.getPassword().equals(user.getPassword())) {
            throw new PasswordNotMatchedException(user.getId());
        }

        return getUserResponseDto(user);
    }

    private UserResponseDto getUserResponseDto(User user) {
        return Mappers.toUserCreateResponseDto(user, jwtTokenProvider.createToken(user.getId()));
    }

}
