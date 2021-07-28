package io.realworld.user.domain;

import io.realworld.common.base.BaseTimeEntity;
import io.realworld.common.exception.PasswordNotMatchedException;
import io.realworld.user.api.UserPasswordEncoder;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.persistence.*;

@Getter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private String username;

    private String bio;

    private String image;

    public static User createWithUserPasswordEncoder(String email, String password, UserPasswordEncoder userPasswordEncoder, String username) {
        return new User(email, password, userPasswordEncoder, username);
    }

    @Builder
    public static User createWithUserPasswordEncoder(Long id, String email, String password, UserPasswordEncoder userPasswordEncoder, String username, String bio, String image) {
        return new User(id, email, password, userPasswordEncoder, username, bio, image);
    }

    private User(String email, String password, UserPasswordEncoder userPasswordEncoder, String username) {
        this(null, email, password, userPasswordEncoder, username, null, null);
    }

    private User(Long id, String email, String password, UserPasswordEncoder userPasswordEncoder, String username, String bio, String image) {
        Assert.state(StringUtils.isNotBlank(email), "email may not be blank.");
        Assert.state(StringUtils.isNotBlank(password), "password may not be blank.");
        Assert.state(StringUtils.isNotBlank(username), "username may not be blank.");
        Assert.state(userPasswordEncoder != null, "userPasswordEncoder may not be null.");

        this.id = id;
        this.email = email;
        this.password = this.encodePassword(password, userPasswordEncoder);
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

    public void updateUserInfo(String email, String username, String password, UserPasswordEncoder passwordEncoder, String image, String bio) {
        this.email = email;
        if (StringUtils.isNotBlank(password)) {
            this.password = this.encodePassword(password, passwordEncoder);
        }
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

    public String encodePassword(String password, UserPasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(password);
    }

    public void checkPassword(String password, UserPasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(password, this.password)) {
            throw new PasswordNotMatchedException(this.getId());
        }
    }
}
