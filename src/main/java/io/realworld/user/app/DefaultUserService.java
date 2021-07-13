package io.realworld.user.app;

import io.realworld.user.api.UserService;
import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserCreateResponseDto;
import io.realworld.user.api.dto.UserUpdateRequestDto;
import io.realworld.user.app.dto.UserMappers;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class DefaultUserService implements UserService {

    final private UserRepository userRepository;

    @Override
    public UserCreateResponseDto createUser(UserCreateRequestDto dto) {
        User user = userRepository.save(dto.toEntity());
        return UserMappers.toUserCreateResponseDto(user, dto.getPassword());
    }

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        return null;
    }

    @Override
    public User updateUser(UserUpdateRequestDto dto) {
        return userRepository.save(dto.toEntity());
    }


}
