package io.realworld.user.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String email;
    private String password;
    private String token;

    @Embedded
    private Profile profile;

    @Builder
    public User(String email, String password, String token, Profile profile) {
        this.email = email;
        this.password = password;
        this.token = token;
        this.profile = profile;
    }
}
