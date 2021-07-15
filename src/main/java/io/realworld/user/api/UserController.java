package io.realworld.user.api;

import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserLoginRequestDto;
import io.realworld.user.api.dto.UserResponseDto;
import io.realworld.user.app.UserService;
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

    @PostMapping("/users")
    public UserResponseDto createUser(@RequestBody UserCreateRequestDto dto) {
        return userService.createUser(dto);
    }

    @PostMapping("/users/login")
    public UserResponseDto login(@RequestBody UserLoginRequestDto dto) {
        return userService.login(dto);
    }
}