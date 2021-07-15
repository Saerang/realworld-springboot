package io.realworld.user.api;

import io.realworld.user.api.dto.ProfileResponseDto;
import io.realworld.user.app.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @RequestMapping("/profiles/{username}")
    public ProfileResponseDto getProfile(@PathVariable String username) {
        return profileService.getProfile(username);
    }
}

