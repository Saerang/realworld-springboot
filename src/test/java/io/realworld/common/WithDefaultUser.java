package io.realworld.common;


import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = "realworld1@email.com", password = "12345678")
public @interface WithDefaultUser {
}
