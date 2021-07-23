package io.realworld.user.api;

import io.realworld.common.security.JwtTokenProvider;
import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserLoginRequestDto;
import io.realworld.user.api.dto.UserResponseDto;
import io.realworld.user.api.dto.UserUpdateRequestDto;
import io.realworld.user.app.AuthenticationService;
import io.realworld.user.app.UserService;
import io.realworld.user.app.dto.Mappers;
import io.realworld.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    final private UserService userService;
    final private AuthenticationService authenticationService;
    final private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/users")
    public UserResponseDto createUser(@RequestBody UserCreateRequestDto dto) {
        User user = userService.createUser(dto);
        return this.getUserResponseDto(user);
    }

    @PostMapping("/users/login")
    public UserResponseDto login(@RequestBody UserLoginRequestDto dto) {
        User user = userService.login(dto);
        return this.getUserResponseDto(user);
    }

    @GetMapping("/user")
    public UserResponseDto getCurrentUser() {
        User user = userService.getCurrentUser();
        return this.getUserResponseDto(user);
    }

    @PutMapping("/user")
    public UserResponseDto updateUser(@RequestBody UserUpdateRequestDto dto) {
        User user = userService.updateUser(dto, getCurrentUserId());
        return this.getUserResponseDto(user);
    }

    private Long getCurrentUserId() {
        return authenticationService.getCurrentUserId();
    }

    private UserResponseDto getUserResponseDto(User user) {
        return Mappers.toUserCreateResponseDto(user, jwtTokenProvider.createToken(user.getEmail()));
    }
}
