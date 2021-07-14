package io.realworld.user.api;

import io.realworld.common.security.TokenProvider;
import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    final private UserService userService;
    final private TokenProvider tokenProvider;

    @PostMapping("/users")
    public UserResponseDto createUser(@RequestBody UserCreateRequestDto dto) {
        return userService.createUser(dto);
    }

}


