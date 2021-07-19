package io.realworld.user.api;

import io.realworld.user.api.dto.ProfileResponseDto;
import io.realworld.user.app.AuthenticationService;
import io.realworld.user.app.ProfileService;
import io.realworld.user.app.dto.Mappers;
import io.realworld.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final AuthenticationService authenticationService;

    @GetMapping("/profiles/{username}")
    public ProfileResponseDto getProfile(@PathVariable String username) {
        User user = profileService.getProfile(username);
        return getProfileResponseDto(user);
    }

    @PostMapping("/profiles/{username}/follow")
    public ProfileResponseDto followUser(@PathVariable String username) {
        User user = profileService.followUser(getCurrenctUserEmail(), username);
        return getProfileResponseDto(user);
    }

    @DeleteMapping("/profiles/{username}/follow")
    public ProfileResponseDto unfollowUser(@PathVariable String username) {
        User user = profileService.unfollowUser(getCurrenctUserEmail(), username);
        return getProfileResponseDto(user);
    }

    private long getCurrenctUserEmail() {
        return authenticationService.getCurrentUser().getId();
    }

    private ProfileResponseDto getProfileResponseDto(User user) {
        return Mappers.toProfileResponseDto(user);
    }
}

