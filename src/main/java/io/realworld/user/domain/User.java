package io.realworld.user.domain;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.persistence.*;

@Getter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

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

    public User(String email, String password, String username) {
        this(email, password, username, null, null);
    }

    @Builder
    public User(String email, String password, String username, String bio, String image) {
        Assert.state(StringUtils.isNotBlank(email), "email cannot be null.");
        Assert.state(StringUtils.isNotBlank(password), "password cannot be null.");
        Assert.state(StringUtils.isNotBlank(username), "username cannot be null.");

        this.email = email;
        this.password = password;
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

    public void updateUserInfo(String email, String username, String password, String image, String bio) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

}
