package io.realworld.user.app;

import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserResponseDto;
import io.realworld.user.api.dto.UserUpdateRequestDto;
import io.realworld.user.app.exception.UserAlreadyExist;
import io.realworld.user.domain.Profile;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
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
        UserResponseDto user = userService.createUser(dto);

        em.flush();
        em.clear();

        User findUser = userRepository.findByEmail(user.getEmail()).get();

        //then
        assertThat(user.getEmail()).isEqualTo(findUser.getEmail());
    }

    @Test
    void createUser_dup() {
        //given
        userRepository.save(getDefaultUser());
        UserCreateRequestDto dto = UserCreateRequestDto.builder()
                .username("realworld")
                .email("realworld@gmail.com")
                .password("1234")
                .build();

        //when
        assertThatThrownBy(() -> userService.createUser(dto)).isInstanceOf(UserAlreadyExist.class);
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

    @Test
    void getUserByUsername() {
        //given
        User user = getDefaultUser();
        userRepository.save(user);

        //when
        User findUser = userService.findUserByUsername(user.getProfile().getUsername()).get();

        //then
        assertThat(user.getId()).isEqualTo(findUser.getId());
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

