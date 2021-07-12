package io.realworld.user.app;

import io.realworld.user.api.UserService;
import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserUpdateRequestDto;
import io.realworld.user.domain.Profile;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private EntityManager em;
    @Autowired
    private UserRepository userRepository;

    @Test
    void createUser() {
        //given
        UserCreateRequestDto dto = UserCreateRequestDto.builder()
                .username("realworld")
                .email("realworld@gmail.com")
                .password("1234")
                .build();

        //when
        User user = userService.createUser(dto);
        assertThat(user.getId()).isGreaterThan(0);

        em.flush();
        em.clear();

        User findUser = userRepository.findById(user.getId()).get();

        //then
        assertThat(user.getId()).isEqualTo(findUser.getId());
    }

    @Test
    void updateUser() {
        //given
        userRepository.save(getDefaultUser());

        String bio = "update bio";
        String email = "update@gmail.com";
        String image = "update image";

        UserUpdateRequestDto dto = UserUpdateRequestDto.builder()
                .bio(bio)
                .email(email)
                .image(image)
                .build();

        //when
        User user = userService.updateUser(dto);
        User findUser = userRepository.findById(user.getId()).get();

        //then
        assertThat(findUser.getEmail()).isEqualTo(email);
        assertThat(findUser.getProfile().getBio()).isEqualTo(bio);
        assertThat(findUser.getProfile().getImage()).isEqualTo(image);
    }

    private User getDefaultUser() {
        Profile profile = Profile.builder()
                .username("realworld")
                .bio("I work at statefarm")
                .build();
        return User.builder()
                .profile(profile)
                .email("realworld@gmail.com")
                .build();
    }

}
